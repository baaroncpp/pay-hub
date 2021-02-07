package com.jajjamind.payvault.core.service.account;

import com.jajjamind.commons.time.DateTimeUtil;
import com.jajjamind.commons.utils.RealTimeUtil;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.account.models.CashFlow;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.api.users.models.Approval;
import com.jajjamind.payvault.core.jpa.models.account.TAccount;
import com.jajjamind.payvault.core.jpa.models.account.TAccountMapping;
import com.jajjamind.payvault.core.jpa.models.account.TAccountTransaction;
import com.jajjamind.payvault.core.jpa.models.account.TCashflowRequest;
import com.jajjamind.payvault.core.jpa.models.agent.TAgent;
import com.jajjamind.payvault.core.jpa.models.enums.*;
import com.jajjamind.payvault.core.jpa.models.product.TProduct;
import com.jajjamind.payvault.core.jpa.models.user.TUserMeta;
import com.jajjamind.payvault.core.repository.account.AccountRepository;
import com.jajjamind.payvault.core.service.utilities.AccountUtilities;
import com.jajjamind.payvault.core.repository.account.CashFlowRequestRepository;
import com.jajjamind.payvault.core.repository.agent.AgentRepository;
import com.jajjamind.payvault.core.repository.bank.AccountMappingRepository;
import com.jajjamind.payvault.core.repository.product.ProductRepository;
import com.jajjamind.payvault.core.security.models.LoggedInUser;
import com.jajjamind.payvault.core.utils.AuditService;
import com.jajjamind.payvault.core.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

/**
* @author akena
* 04/02/2021
* 03:55
**/
@Service
public class CashFlowServiceImpl implements CashFlowService {

    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    public AccountMappingRepository accountMappingRepository;

    @Autowired
    public AgentRepository agentRepository;

    @Autowired
    public AuditService auditService;

    @Autowired
    public CashFlowRequestRepository cashFlowRepository;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public AccountTransactionService accountTransactionService;

    private static final AccountTypeEnum[] SERVICE_PROVIDER_ACCOUNTS = new AccountTypeEnum[]{
            AccountTypeEnum.BULK_PAYMENT,
            AccountTypeEnum.COLLECTION,
            AccountTypeEnum.COMMISSION,
            AccountTypeEnum.PAYOUT};


    @Override
    public void initiateToBusinessUserTransfer(CashFlow cashFlowRequest) {

        Validate.notNull(cashFlowRequest.getBusinessUser(),"BusinessAccount ID is missing");
        Validate.notNull(cashFlowRequest.getMainAccountId(),ErrorMessageConstants.MAIN_ACCOUNT_ID_MISSING);

        final TAccount mainAccount = getMainAccount(cashFlowRequest.getMainAccountId());

        Optional<TAgent> agentOptional = agentRepository.findById(cashFlowRequest.getBusinessUser());
        Validate.isPresent(agentOptional, ErrorMessageConstants.AGENT_WITH_ID_NOT_FOUND,cashFlowRequest.getBusinessUser());

        final TAgent agent = agentOptional.get();

        Optional<TAccountMapping> businessUserAccMap = accountMappingRepository.findAgentAccountMapped(agent.getId());
        Validate.isPresent(businessUserAccMap,"No active account found for business user with id %s",agent.getId());

        final TAccount businessAccount = businessUserAccMap.get().getAccountId();

        AccountUtilities.checkThatAccountCanTransact(businessAccount,AccountTypeEnum.BUSINESS);
        AccountUtilities.checkThatTransactionWontResultInNegativeBalance(mainAccount,cashFlowRequest.getAmount());

        //Create transfer request
        final TCashflowRequest cashflowRequest = getCashFlowRequestTemplate(mainAccount,businessAccount,
                cashFlowRequest,CashFlowEnum.MAIN_TO_BUSINESS);
        cashflowRequest.setAgent(agent);

        auditService.stampAuditedEntity(cashflowRequest);
        cashFlowRepository.save(cashflowRequest);

    }

    private TAccount getMainAccount(Long id){
        Optional<TAccount> account = accountRepository.findActiveByIdAndType(id, AccountTypeEnum.MAIN.name());
        Validate.isPresent(account,"Main account with ID %s could not be found");
        AccountUtilities.checkThatAccountCanTransact(account.get(),AccountTypeEnum.MAIN);

        return account.get();
    }

    private TCashflowRequest getCashFlowRequestTemplate(TAccount mainAccount,TAccount normalAccount, CashFlow request, CashFlowEnum cashflowType){
        final TCashflowRequest cashflowRequest = new TCashflowRequest();
        switch (cashflowType){
            case BUSINESS_TO_MAIN:
            case PROVIDER_LIQUIDATION:
            case STOCK_WITHDRAW:
                cashflowRequest.setToAccount(mainAccount);
                cashflowRequest.setFromAccount(normalAccount);
                break;
            case MAIN_TO_BUSINESS:
                cashflowRequest.setFromAccount(mainAccount);
                cashflowRequest.setToAccount(normalAccount);
                break;
            default:
                throw new IllegalStateException("Unkown cashflow type");

        }
        cashflowRequest.setFromAccount(mainAccount);
        cashflowRequest.setAmount(request.getAmount());
        cashflowRequest.setNote(request.getNote());
        cashflowRequest.setExternalReference(RealTimeUtil.generateTransactionId());
        cashflowRequest.setFlowType(cashflowType);
        cashflowRequest.setStatus(ApprovalEnum.PENDING);

        return cashflowRequest;
    }

    @Transactional
    @Override
    public CashFlow approveToBusinessUserTransfer(Approval approval) {

        final TCashflowRequest request = getCashFlowRequestAndValidateType(approval.getId(),CashFlowEnum.MAIN_TO_BUSINESS);
        final ApprovalEnum status = approval.getStatus();
        final Date date = DateTimeUtil.getCurrentUTCTime();

        switch (status){
            case REJECTED:
                rejectCashFlowRequest(request,approval.getNote());
                break;
            case APPROVED:
                resolveApprover(request,approval,date);

                if(request.getApprovalCount() == 2){

                    approveRequest(request,date,new AccountTypeEnum[]{AccountTypeEnum.MAIN},new AccountTypeEnum[]{AccountTypeEnum.BUSINESS});

                }

                auditService.stampAuditedEntity(request);
                cashFlowRepository.save(request);
                break;
            default:
                throw new IllegalStateException(ErrorMessageConstants.APPROVAL_STATUS_UNKNOWN);

        }

        return getCashFlowObject(request);
    }

    @Override
    public void initiateToServiceProviderTransfer(CashFlow cashFlowRequest) {
        Validate.notNull(cashFlowRequest.getMainAccountId(),ErrorMessageConstants.MAIN_ACCOUNT_ID_MISSING);
        Validate.notNull(cashFlowRequest.getProductAccountId(),"Product ID cannot be empty");

        Optional<TProduct> productOptional = productRepository.findProductWithAccount(cashFlowRequest.getProductAccountId());
        Validate.isPresent(productOptional,"Product with not found");

        final TAccount productAccount = productOptional.get().getProductAccount();
        AccountUtilities.checkThatAccountCanTransact(productAccount, SERVICE_PROVIDER_ACCOUNTS);

        AccountUtilities.checkThatTransactionWontResultInNegativeBalance(productAccount,cashFlowRequest.getAmount());

        TAccount tAccountMain = getMainAccount(cashFlowRequest.getMainAccountId());

        final TCashflowRequest request = getCashFlowRequestTemplate(tAccountMain,productAccount,cashFlowRequest, CashFlowEnum.PROVIDER_LIQUIDATION);
        request.setProduct(productOptional.get());

        auditService.stampAuditedEntity(request);
        cashFlowRepository.save(request);

    }

    @Transactional
    @Override
    public CashFlow approveToServiceProviderRequest(Approval approval) {

        final TCashflowRequest request = getCashFlowRequestAndValidateType(approval.getId(),CashFlowEnum.PROVIDER_LIQUIDATION);
        final ApprovalEnum status = approval.getStatus();
        final Date date = DateTimeUtil.getCurrentUTCTime();

        switch (status){
            case REJECTED:
                rejectCashFlowRequest(request,approval.getNote());
                break;
            case APPROVED:
                resolveApprover(request,approval,date);

                if(request.getApprovalCount() == 2){

                  approveRequest(request,date,SERVICE_PROVIDER_ACCOUNTS,new AccountTypeEnum[]{AccountTypeEnum.MAIN});

                }

                auditService.stampAuditedEntity(request);
                cashFlowRepository.save(request);
                break;
            default:
                throw new IllegalStateException(ErrorMessageConstants.APPROVAL_STATUS_UNKNOWN);

        }

        return getCashFlowObject(request);
    }

    @Override
    public void initiateStockWithdraw(CashFlow cashFlow) {
        //Requires only 1 approval
        final TAccount account = getMainAccount(cashFlow.getMainAccountId());
        final TCashflowRequest request = getCashFlowRequestTemplate(account,null,cashFlow,CashFlowEnum.STOCK_WITHDRAW);

        auditService.stampAuditedEntity(request);
        cashFlowRepository.save(request);

    }

    @Transactional
    @Override
    public CashFlow approveStockWithdraw(Approval approval) {

        //Only one approval required for stock withdraw
        final TCashflowRequest request = getCashFlowRequestAndValidateType(approval.getId(),CashFlowEnum.STOCK_WITHDRAW);

        if(approval.getStatus().equals(ApprovalEnum.APPROVED)){


            Optional<TAccount> account = accountRepository.findByIdForBalanceUpdate(request.getFromAccount().getId());
            Validate.isPresent(account,"Account to withdraw from not found");

            TAccount fromAccount = account.get();
            AccountUtilities.checkThatAccountCanTransact(fromAccount,AccountTypeEnum.MAIN);
            AccountUtilities.checkThatTransactionWontResultInNegativeBalance(fromAccount,request.getAmount());

            final Money balanceBefore = fromAccount.getAvailableBalance();
            final Money balanceAfter = (Money)balanceBefore.subtract(request.getAmount());
            fromAccount.setAvailableBalance(balanceAfter);
            Date  date = DateTimeUtil.getCurrentUTCTime();
            auditService.stampAuditedEntity(fromAccount);
            fromAccount.setModifiedOn(date);

            accountRepository.save(fromAccount);

            final TAccountTransaction fromAccountTransaction = getTemplateAccountTransaction(fromAccount, request.getExternalReference())
                    .withBalanceBefore(balanceBefore)
                    .withBalanceAfter(balanceAfter)
                    .withTransactionType(TransactionTypeEnum.CASH_FLOW_DEBIT)
                    .withCreatedOn(date)
                    .build();

            final TAccountTransaction savedFromAccountTransaction = accountTransactionService.save(fromAccountTransaction);

            request.setFromAccTransaction(savedFromAccountTransaction);

            request.setApprover1(getApprovingUserMeta());
            request.setFirstApprovedOn(date);
            request.setNote1(approval.getNote());
            request.setStatus(ApprovalEnum.APPROVED);

            auditService.stampAuditedEntity(request);
            cashFlowRepository.save(request);
        }else if(approval.getStatus().equals(ApprovalEnum.REJECTED)){

            rejectCashFlowRequest(request,approval.getNote());

        }else{
            throw new IllegalStateException("Unknown approval option received");
        }

        return getCashFlowObject(request);
    }


    @Override
    public void initiateFromBusinessUserTransfer(CashFlow cashFlow) {
        Validate.notNull(cashFlow.getBusinessUser(),"BusinessAccount ID is missing");
        Validate.notNull(cashFlow.getMainAccountId(),ErrorMessageConstants.MAIN_ACCOUNT_ID_MISSING);

        Optional<TAgent> agentOptional = agentRepository.findById(cashFlow.getBusinessUser());
        Validate.isPresent(agentOptional, ErrorMessageConstants.AGENT_WITH_ID_NOT_FOUND,cashFlow.getBusinessUser());

        final TAgent agent = agentOptional.get();

        Optional<TAccountMapping> businessUserAccMap = accountMappingRepository.findAgentAccountMapped(agent.getId());
        Validate.isPresent(businessUserAccMap,"No active account found for business user with id %s",agent.getId());

        final TAccount businessAccount = businessUserAccMap.get().getAccountId();
        AccountUtilities.checkThatAccountCanTransact(businessAccount,AccountTypeEnum.BUSINESS);

        AccountUtilities.checkThatTransactionWontResultInNegativeBalance(businessAccount,cashFlow.getAmount());

        final TAccount account = getMainAccount(cashFlow.getMainAccountId());
        final TCashflowRequest request = getCashFlowRequestTemplate(account,businessAccount,cashFlow,CashFlowEnum.BUSINESS_TO_MAIN);
        request.setAgent(agent);

        auditService.stampAuditedEntity(request);
        cashFlowRepository.save(request);


    }

    @Transactional
    @Override
    public CashFlow approveFromBusinessUserTransfer(Approval approval) {
        final TCashflowRequest request = getCashFlowRequestAndValidateType(approval.getId(),CashFlowEnum.BUSINESS_TO_MAIN);
        final ApprovalEnum status = approval.getStatus();
        final Date date = DateTimeUtil.getCurrentUTCTime();

        switch (status){
            case REJECTED:
                rejectCashFlowRequest(request,approval.getNote());
                break;
            case APPROVED:
                resolveApprover(request,approval,date);

                if(request.getApprovalCount() == 2){

                    approveRequest(request,date,new AccountTypeEnum[]{AccountTypeEnum.BUSINESS},new AccountTypeEnum[]{AccountTypeEnum.MAIN});

                }

                auditService.stampAuditedEntity(request);
                cashFlowRepository.save(request);
                break;
            default:
                throw new IllegalStateException(ErrorMessageConstants.APPROVAL_STATUS_UNKNOWN);

        }

        return getCashFlowObject(request);
    }

    private TCashflowRequest getCashFlowRequestAndValidateType(Long id,CashFlowEnum flow){
        Optional<TCashflowRequest> request = cashFlowRepository.findByIdForApproval(id);
        Validate.isPresent(request,"Cash flow request with id %s not found",id);

        TCashflowRequest r = request.get();
        Validate.isTrue(r.getFlowType().equals(flow),"Cash flow type does not match approval requested");
        Validate.isTrue(r.getStatus().equals(ApprovalEnum.PENDING),ErrorMessageConstants.NO_PENDING_APPROVAL_FOUND);
        Validate.notNull(r.getFromAccount(),"Cash out account not found");
        Validate.isTrue(r.getAmount().isGreaterThanZero(),"Approval amount must be greater than 0");

        return r;
    }


    private TUserMeta getApprovingUserMeta(){
        LoggedInUser user = auditService.getLoggedInUser();
        Validate.notNull(user.getId(),"Approving user could not be found");
        final TUserMeta approvingUser = new TUserMeta();
        approvingUser.setUserId(user.getId());


        return approvingUser;
    }

    private void rejectCashFlowRequest(TCashflowRequest request,String note){

        request.setRejectedBy(getApprovingUserMeta());
        request.setStatus(ApprovalEnum.REJECTED);

        if (request.getApprovalCount() == 0) {
            request.setNote1(note);
        } else {
            request.setNote2(note);
        }

        auditService.stampAuditedEntity(request);
        cashFlowRepository.save(request);
    }

    private TAccountTransaction.Builder getTemplateAccountTransaction(TAccount account,String externalTransactionId){
        return new TAccountTransaction.Builder(account)
                .withTransactionStatus(TransactionStatusEnum.SUCCESSFUL)
                .withNonReversal(Boolean.TRUE)
                .withExternalTransactionId(externalTransactionId);
    }

    private CashFlow getCashFlowObject(TCashflowRequest request){
        final CashFlow flow = new CashFlow();
        flow.setAmount(request.getAmount());
        flow.setInitiatorReference(request.getExternalReference());
        flow.setNote(request.getNote());

        return flow;
    }

    private void resolveApprover(TCashflowRequest request, Approval approval,Date date){
        TUserMeta approvingUser = getApprovingUserMeta();
        if(request.getApprovalCount() < 1){
            request.setApprovalCount(1);
            request.setApprover1(approvingUser);
            request.setNote1(approval.getNote());
            request.setFirstApprovedOn(date);

        }else if(request.getApprovalCount() > 0){
            request.setApprover2(approvingUser);
            request.setApprovalCount(2);
            request.setNote2(approval.getNote());
            request.setSecondApprovedOn(date);

        }else{
            throw new IllegalStateException("Unknown state for total approvals");
        }

    }

    private void approveRequest(TCashflowRequest request,Date date,AccountTypeEnum[] fromAccountType,AccountTypeEnum[] toAccountType){

        final BigDecimal requestedAmount = request.getAmount();

        final Optional<TAccount> fromAccountOptional = accountRepository.findByIdForBalanceUpdate(request.getFromAccount().getId());
        final Optional<TAccount> toAccountOptional = accountRepository.findByIdForBalanceUpdate(request.getToAccount().getId());

        Validate.isPresent(toAccountOptional,ErrorMessageConstants.ACCOUNT_WITH_ID_NOT_FOUND,request.getToAccount().getId());
        final TAccount toAccount = toAccountOptional.get();
        AccountUtilities.checkThatAccountCanTransact(toAccount,toAccountType);

        Validate.isPresent(fromAccountOptional,ErrorMessageConstants.ACCOUNT_WITH_ID_NOT_FOUND,request.getFromAccount().getId());
        final TAccount fromAccount = fromAccountOptional.get();

        AccountUtilities.checkThatAccountCanTransact(fromAccount,fromAccountType);
        AccountUtilities.checkThatTransactionWontResultInNegativeBalance(fromAccount,requestedAmount);

        final Money currentFromAccountBalance = fromAccount.getAvailableBalance();
        final Money finalFromAccountBalance = (Money)fromAccount.getAvailableBalance().subtract(requestedAmount);

        fromAccount.setAvailableBalance(finalFromAccountBalance);
        auditService.stampAuditedEntity(fromAccount);
        accountRepository.save(fromAccount);

        final Money currentToAccountBalance = toAccount.getAvailableBalance();
        final Money finalToAccountBalance = (Money)toAccount.getAvailableBalance().add(requestedAmount);

        toAccount.setAvailableBalance(finalFromAccountBalance);
        auditService.stampAuditedEntity(toAccount);
        accountRepository.save(toAccount);

        final TAccountTransaction fromAccountTransaction = getTemplateAccountTransaction(fromAccount,request.getExternalReference())
                .withBalanceBefore(currentFromAccountBalance)
                .withBalanceAfter(finalFromAccountBalance)
                .withTransactionType(TransactionTypeEnum.CASH_FLOW_DEBIT)
                .withCreatedOn(date)
                .build();

        request.setFromAccTransaction(
                accountTransactionService.save(fromAccountTransaction));

        final TAccountTransaction toAccountTransaction = getTemplateAccountTransaction(fromAccount,request.getExternalReference())
                .withBalanceBefore(currentToAccountBalance)
                .withBalanceAfter(finalToAccountBalance)
                .withTransactionType(TransactionTypeEnum.CASH_FLOW_CREDIT)
                .withCreatedOn(date)
                .build();

        request.setToAccTransaction(
                accountTransactionService.save(toAccountTransaction));
    }
}
