package com.jajjamind.payvault.core.service.bank;

import com.jajjamind.commons.time.DateTimeUtil;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.account.models.Account;
import com.jajjamind.payvault.core.api.bank.models.BankDeposit;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.api.users.models.Approval;
import com.jajjamind.payvault.core.jpa.models.account.TAccount;
import com.jajjamind.payvault.core.jpa.models.bank.TBankAccount;
import com.jajjamind.payvault.core.jpa.models.account.TAccountMapping;
import com.jajjamind.payvault.core.jpa.models.bank.TBankDeposit;
import com.jajjamind.payvault.core.jpa.models.bank.TBankDepositApproval;
import com.jajjamind.payvault.core.jpa.models.enums.*;
import com.jajjamind.payvault.core.jpa.models.user.TUser;
import com.jajjamind.payvault.core.repository.account.AccountRepository;
import com.jajjamind.payvault.core.service.utilities.AccountUtilities;
import com.jajjamind.payvault.core.repository.bank.AccountMappingRepository;
import com.jajjamind.payvault.core.repository.bank.BankAccountRepository;
import com.jajjamind.payvault.core.repository.bank.BankDepositApprovalRepository;
import com.jajjamind.payvault.core.repository.bank.BankDepositRepository;
import com.jajjamind.payvault.core.security.models.LoggedInUser;
import com.jajjamind.payvault.core.utils.AuditService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author akena
 * 02/02/2021
 * 00:52
 **/
@Service
public class BankDepositServiceImpl implements BankDepositService{

    @Autowired
    public BankDepositRepository bankDepositRepository;

    @Autowired
    public AccountMappingRepository bankAccountMappingRepository;

    @Autowired
    public BankAccountRepository bankAccountRepository;

    @Autowired
    public AccountRepository systemAccountRepository;

    @Autowired
    public BankDepositApprovalRepository bankDepositApprovalRepository;

    @Autowired
    public AuditService auditService;

    @Transactional
    @Override
    public BankDeposit initiateDeposit(BankDeposit deposit) {
        deposit.validate();

        Optional<TBankAccount> bankAccount = bankAccountRepository.findById(deposit.getBank().getId());

        Validate.isPresent(bankAccount,"Bank with ID %s not found in the system");

        final TBankDeposit bankDeposit = new TBankDeposit();
        BeanUtils.copyProperties(deposit,bankDeposit);
        bankDeposit.setBank(bankAccount.get());
        bankDeposit.setStatus(TransactionStatusEnum.PENDING);

        auditService.stampAuditedEntity(bankDeposit);
        bankDepositRepository.save(bankDeposit);

        //Create approval request
        TBankDepositApproval approvalRequest = new TBankDepositApproval();
        approvalRequest.setBankDeposit(bankDeposit);
        approvalRequest.setStatus(ApprovalEnum.PENDING);
        approvalRequest.setApprovalCount(0);

        auditService.stampAuditedEntity(approvalRequest);
        bankDepositApprovalRepository.save(approvalRequest);


        return deposit;
    }

    @Override
    public Account fetchAccountLinkedToBank(Integer bankId) {

        return   null;
    }

    @Transactional
    @Override
    public Account linkMainAccountToBank(Long accountId,Integer bankId) {

        Optional<TAccountMapping> accountMapping = bankAccountMappingRepository.findBankAccountMapped(bankId);
        Validate.isTrue(!accountMapping.isPresent(),"Bank account already has an active main account mapped");

        Optional<TAccount> accountToLink = systemAccountRepository.findById(accountId);
        Validate.isPresent(accountToLink, ErrorMessageConstants.ACCOUNT_WITH_ID_NOT_FOUND,accountId);

        final TAccount acc = accountToLink.get();
        AccountUtilities.checkThatAccountCanBeAssignedAsMain(acc);

        final TAccountMapping mapping = new TAccountMapping();
        mapping.setBankId(bankId);
        mapping.setAccountId(acc);
        mapping.setStatus(StatusEnum.ACTIVE);

        auditService.stampAuditedEntity(mapping);
        bankAccountMappingRepository.save(mapping);

        acc.setAssigned(Boolean.TRUE);
        acc.setAccountStatus(AccountStatusEnum.ACTIVE);
        auditService.stampAuditedEntity(acc);
        systemAccountRepository.save(acc);

        final Account accountR = new Account();
        BeanUtils.copyProperties(acc,accountR);
        return accountR;
    }

    @Transactional
    @Override
    public Account unlinkMainAccountFromBank(Long accountId, Integer bankId) {

        Optional<TAccountMapping> accountMapping = bankAccountMappingRepository.findBankAccountMapped(bankId);
        Validate.isPresent(accountMapping,"Bank is not linked to this account");
        Validate.isTrue(accountMapping.get().getStatus().equals(StatusEnum.ACTIVE),ErrorMessageConstants.ACCOUNT_MAPPING_FOR_BANK_DOES_NOT_EXIST,accountId,bankId);

        Optional<TAccount> accountToUnlink = systemAccountRepository.findById(accountId);
        Validate.isPresent(accountToUnlink, ErrorMessageConstants.ACCOUNT_WITH_ID_NOT_FOUND,accountId);

        final TAccount account = accountToUnlink.get();

        AccountUtilities.checkThatAccountCanBeUnAssigned(account);

        final TAccountMapping mapping = accountMapping.get();
        mapping.setStatus(StatusEnum.NOT_ACTIVE);
        auditService.stampAuditedEntity(mapping);

        bankAccountMappingRepository.save(mapping);

        account.setAccountStatus(AccountStatusEnum.CLOSED);
        account.setStatusDescription(AccountStatusEnum.CLOSED.getDescription());
        account.setAssigned(Boolean.FALSE);

        auditService.stampAuditedEntity(account);
        systemAccountRepository.save(account);

        Account a = new Account();
        BeanUtils.copyProperties(account,a);

        return a;
    }

    @Transactional
    @Override
    public void approveBankDeposit(Approval toApprove) {

        Optional<TBankDepositApproval> mApprovalOptional = bankDepositApprovalRepository.findBankDepositWithBankDetails(toApprove.getId());
        Validate.isPresent(mApprovalOptional,"Pending approval with ID %s could not be found",toApprove);

        TBankDepositApproval approval = mApprovalOptional.get();

        Validate.isTrue(approval.getApprovalCount() < 2,"Agent creation has been approved");
        Validate.isTrue(approval.getStatus().equals(ApprovalEnum.PENDING),"Agent creation has been approved");

        final TBankDeposit deposit = approval.getBankDeposit();
        final TBankAccount bank = deposit.getBank();

        Validate.notNull(deposit,"Deposit details could not be fetched");
        Validate.notNull(bank,"Bank details could not be fetched");

        Optional<TAccountMapping> mapping = bankAccountMappingRepository.findBankAccountMapped(bank.getId());
        Validate.isPresent(mapping,ErrorMessageConstants.ACCOUNT_MAPPING_FOR_BANK_DOES_NOT_EXIST,bank.getId());

        LoggedInUser user = auditService.getLoggedInUser();

        final TUser approvingUser = new TUser();
        approvingUser.setId(user.getId());

        if(approval.getApprover1() == null){
            approval.setApprover1(approvingUser);
            approval.setApprovalCount(1);
            approval.setNote(toApprove.getNote());
            approval.setFirstApproveOn(DateTimeUtil.getCurrentUTCTime());
        }else {
            approval.setApprover2(approvingUser);
            approval.setNote2(toApprove.getNote());
            approval.setApprovalCount(approval.getApprovalCount()+1);
            approval.setSecondApproveOn(DateTimeUtil.getCurrentUTCTime());
        }

        auditService.stampAuditedEntity(approval);
        bankDepositApprovalRepository.save(approval);

        final BigDecimal amountToTopUp = deposit.getAmountDeposited();
        Optional<TAccount> accountToUpdate = systemAccountRepository.findByIdForBalanceUpdate(mapping.get().getId());

        Validate.isPresent(accountToUpdate,ErrorMessageConstants.ACCOUNT_WITH_ID_NOT_FOUND);

        final TAccount account = accountToUpdate.get();
        account.setAvailableBalance(account.getAvailableBalance().add(amountToTopUp));

        auditService.stampAuditedEntity(account);
        systemAccountRepository.save(account);

        deposit.setStatus(TransactionStatusEnum.SUCCESSFUL);
        auditService.stampAuditedEntity(deposit);
        bankDepositRepository.save(deposit);

    }
}
