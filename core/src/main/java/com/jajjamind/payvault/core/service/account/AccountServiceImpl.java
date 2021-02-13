package com.jajjamind.payvault.core.service.account;

import com.jajjamind.commons.text.StringUtil;
import com.jajjamind.commons.time.DateTimeUtil;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.account.models.Account;
import com.jajjamind.payvault.core.api.account.models.AccountLink;
import com.jajjamind.payvault.core.api.account.models.AccountingGroup;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.jpa.models.account.TAccount;
import com.jajjamind.payvault.core.jpa.models.account.TAccountGrouping;
import com.jajjamind.payvault.core.jpa.models.account.TAccountMapping;
import com.jajjamind.payvault.core.jpa.models.agent.TAgent;
import com.jajjamind.payvault.core.jpa.models.enums.AccountStatusEnum;
import com.jajjamind.payvault.core.jpa.models.enums.AccountTypeEnum;
import com.jajjamind.payvault.core.jpa.models.enums.ApprovalEnum;
import com.jajjamind.payvault.core.jpa.models.enums.StatusEnum;
import com.jajjamind.payvault.core.jpa.models.user.TGroup;
import com.jajjamind.payvault.core.jpa.models.user.TUser;
import com.jajjamind.payvault.core.repository.JooqFilter;
import com.jajjamind.payvault.core.repository.account.AccountGroupingRepository;
import com.jajjamind.payvault.core.repository.account.AccountRepository;
import com.jajjamind.payvault.core.repository.account.JooqAccountRepository;
import com.jajjamind.payvault.core.repository.agent.AgentRepository;
import com.jajjamind.payvault.core.repository.bank.AccountMappingRepository;
import com.jajjamind.payvault.core.security.models.LoggedInUser;
import com.jajjamind.payvault.core.service.utilities.AccountUtilities;
import com.jajjamind.payvault.core.utils.AuditService;
import com.jajjamind.payvault.core.utils.BeanUtilsCustom;
import com.jajjamind.payvault.core.utils.Money;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author akena
 * 14/01/2021
 * 21:45
 **/
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    public AccountGroupingRepository accountGroupingRepository;

    @Autowired
    public AuditService auditService;

    @Autowired
    public JooqAccountRepository jooqAccountRepository;

    @Autowired
    public AccountMappingRepository accountMappingRepository;

    @Autowired
    public AgentRepository agentRepository;

    private static final Integer ACCOUNT_CODE_LENGTH = 6;

    @Override
    public Account addAccount(Account account) {

        account.validate();
        validateAccountingGroup(account);

        TAccount tAccount = new TAccount();
        BeanUtilsCustom.copyProperties(account,tAccount);

        //Only non main accounts can be grouped
        if(account.getAccountGrouping() != null && !account.getAccountType().equals(AccountTypeEnum.MAIN)) {

            Optional<TAccountGrouping> groupingOptional = accountGroupingRepository.findById(account.getAccountGrouping().getId());
            Validate.isPresent(groupingOptional,"Account grouping provided could not be found");

            final TAccountGrouping group = groupingOptional.get();
            Validate.isTrue(group.getGroupType().equals(account.getAccountType()),"All accounts need to be of type %s to be added to group %s",group.getGroupType(),group.getName());

            tAccount.setAccountGrouping(group);
        }

        tAccount.setCode("000");

        final Date creationDate = DateTimeUtil.getCurrentUTCTime();
        tAccount.setBalanceNotificationSentOn(creationDate);
        tAccount.setCreatedOn(creationDate);
        tAccount.setAvailableBalance(BigDecimal.ZERO);
        tAccount.setAccountStatus(AccountStatusEnum.NOT_ACTIVE);
        tAccount.setStatusDescription(AccountStatusEnum.NOT_ACTIVE.getDescription());
        tAccount.setAssigned(Boolean.FALSE);

        auditService.stampAuditedEntity(tAccount);
        accountRepository.save(tAccount);

        Optional<TAccount> createdAccount = accountRepository.findByName(account.getName());
        Validate.isTrue(createdAccount.isPresent(),"Account creation has failed");

        TAccount ca = createdAccount.get();
        ca.setCode(generateAccountCode(account.getAccountType(),ca.getId()));
        accountRepository.save(ca);

        BeanUtilsCustom.copyProperties(createdAccount.get(),account);
        account.setCode(ca.getCode());

        return account;
    }

    @Override
    public AccountingGroup addAccountGrouping(AccountingGroup accountGroup) {

        accountGroup.validate();
        TAccountGrouping group = new TAccountGrouping();
        BeanUtilsCustom.copyProperties(accountGroup,group);

        auditService.stampAuditedEntity(group);
        accountGroupingRepository.save(group);

        Optional<TAccountGrouping> created = accountGroupingRepository.findByName(accountGroup.getName());
        Validate.isTrue(created.isPresent(),"Failed to create accounting group");

        BeanUtilsCustom.copyProperties(created.get(),accountGroup);
        return accountGroup;
    }

    @Override
    public AccountingGroup updateAccountGrouping(AccountingGroup accountGroup) {
        accountGroup.validate();
        Validate.notNull(accountGroup.getId(),"Id of group to update is required");

        Optional<TAccountGrouping> accountGrouping = accountGroupingRepository.findById(accountGroup.getId());

        Validate.isTrue(accountGrouping.isPresent(),"Failed to find account grouping with ID %s",accountGroup.getId());

        TAccountGrouping grouping = accountGrouping.get();

        if(!accountGroup.getName().equalsIgnoreCase(grouping.getName())){
            Optional<TAccountGrouping> groupingDup = accountGroupingRepository.findByName(accountGroup.getName());
            Validate.isTrue(!groupingDup.isPresent(),"Account grouping with same name already exists");
        }
        grouping.setNote(accountGroup.getNote());
        grouping.setCanBulkLiquidate(accountGroup.getCanBulkLiquidate());
        grouping.setName(accountGroup.getName());

        auditService.stampAuditedEntity(grouping);
        accountGroupingRepository.save(grouping);

        return accountGroup;
    }

    @Override
    public JooqAccountRepository.Result getAccountById(Long id) {

        final MultiValueMap<String,Object> queryMap = new LinkedMultiValueMap<>();
        queryMap.add(JooqFilter.REQUIRED_PROPERTIES,jooqAccountRepository.getAllFields());
        queryMap.add(JooqAccountRepository.FIELD_ID,id);
        queryMap.add("sortBy",JooqAccountRepository.FIELD_ID);
        queryMap.add("limit",1);

       final  List<JooqAccountRepository.Result> accounts = jooqAccountRepository.list(queryMap);

        Validate.isTrue(!accounts.isEmpty(),"Failed to retrieve account information");
        return accounts.get(0);
    }

    @Override
    public Account getAccountByCode(String code) {
        Optional<TAccount> accountOptional = accountRepository.findByCode(code);

        Validate.isTrue(accountOptional.isPresent(),"Account with code %s not found",code);

        TAccount account = accountOptional.get();
        Account mAccount = new Account();
        BeanUtils.copyProperties(account,mAccount);
        return mAccount;
    }

    @Override
    public Account updateAccount(Account account) {
        return null;
    }

    @Override
    public Boolean deactivateAccount(Long id) {

        Optional<TAccount> accountOptional = accountRepository.findById(id);

        Validate.isTrue(accountOptional.isPresent(), ErrorMessageConstants.ACCOUNT_WITH_ID_NOT_FOUND,id);
        TAccount account = accountOptional.get();

        Validate.isTrue(account.getAccountStatus().equals(AccountStatusEnum.ACTIVE),"Only an active account can be suspended");

        account.setAccountStatus(AccountStatusEnum.SUSPENDED);

        Date date = DateTimeUtil.getCurrentUTCTime();
        LoggedInUser user = auditService.getLoggedInUser();
        final TUser s = new TUser();
        s.setId(user.getId());

        account.setSuspendedBy(s);
        account.setSuspendedOn(date);

        auditService.stampAuditedEntity(account);

        accountRepository.save(account);

        return Boolean.TRUE;
    }

    @Override
    public Boolean activateAccount(Long id) {
        Optional<TAccount> accountOptional = accountRepository.findById(id);

        Validate.isTrue(accountOptional.isPresent(),"Account with id %s not found",id);
        TAccount account = accountOptional.get();

        Validate.isTrue(!account.getAccountStatus().equals(AccountStatusEnum.ACTIVE),"Account is already active");
        Validate.isTrue(!account.getAccountStatus().equals(AccountStatusEnum.CLOSED),"A closed account cannot be activated");

        account.setAccountStatus(AccountStatusEnum.ACTIVE);

        Date date = DateTimeUtil.getCurrentUTCTime();
        LoggedInUser user = auditService.getLoggedInUser();

        final TUser s = new TUser();
        s.setId(user.getId());
        account.setActivatedBy(s);
        account.setActivateOn(date);

        auditService.stampAuditedEntity(account);

        accountRepository.save(account);

        return Boolean.TRUE;
    }

    @Override
    public Boolean closeAccount(Long id) {
        Optional<TAccount> accountOptional = accountRepository.findById(id);

        Validate.isTrue(accountOptional.isPresent(),"Account with id %s not found",id);
        TAccount account = accountOptional.get();

        Validate.isTrue(account.getAvailableBalance().compareTo(BigDecimal.ZERO) == 0,"Account balance has to be 0 to close account");
        Validate.isTrue(!account.getAssigned(),"Account that has been assigned cannot be closed");

        closeAccount(account);

        auditService.stampAuditedEntity(account);

        accountRepository.save(account);

        return Boolean.TRUE;
    }

    @Override
    public List<AccountingGroup> getAllAccountingGroup() {

        Iterable<TAccountGrouping> accountGroupingL = accountGroupingRepository.findAll();
        List<AccountingGroup> accountingGroups = new ArrayList<>();

        accountGroupingL.forEach(t ->
        {
            AccountingGroup g =new AccountingGroup();
            BeanUtilsCustom.copyProperties(t,g);
            accountingGroups.add(g);
        });

        return accountingGroups;
    }

    @Override
    public AccountingGroup getAccountingGroupById(Long id) {

        Optional<TAccountGrouping> grouping = accountGroupingRepository.findById(id);
        Validate.isTrue(grouping.isPresent(),"Account grouping with ID %s not found",id);

        AccountingGroup group = new AccountingGroup();
        BeanUtils.copyProperties(grouping.get(),group);

        return group;
    }

    @Override
    public List<Account> getAccountsByGroup(Long id) {

        List<Account> account = new ArrayList<>();
        List<TAccount> accountGrouping = accountRepository.findByAccountGrouping(id);

        accountGrouping.stream().forEach(t -> {
            Account mAccount = new Account();
            BeanUtils.copyProperties(t,mAccount);
            account.add(mAccount);
        });
        return account;
    }

    public void linkAgentCommissionAccount(Long agentId, Long accountId) {

        Optional<TAccountMapping> accountMappingOptional = accountMappingRepository.findAgentCommissionAccountMapped(agentId);
        Validate.isTrue(!accountMappingOptional.isPresent(),"Agent already has a commission account mapped. Disable to assign a new commission account");

        Optional<TAgent> agentOptional = agentRepository.findById(agentId);
        Validate.isPresent(agentOptional,ErrorMessageConstants.AGENT_WITH_ID_NOT_FOUND,agentId);

        TAgent agent = agentOptional.get();

        Validate.isTrue(agent.getNonDisabled(),"Agent has been disabled");
        Validate.isTrue(agent.getNonLocked(),"Agent has been locked");
        Validate.isTrue(agent.getApprovalStatus().equals(ApprovalEnum.APPROVED),"Agent has not been approved in system");

        TAccount accountProvided = getCommissionAccountToAssign(accountId);

        accountProvided.setAssigned(Boolean.TRUE);
        accountProvided.setAccountStatus(AccountStatusEnum.ACTIVE);

        auditService.stampAuditedEntity(accountProvided);
        accountRepository.save(accountProvided);

        TAccountMapping mapping = new TAccountMapping();
        mapping.setAgentIdCommission(agentId);
        mapping.setAccountId(accountProvided);
        mapping.setStatus(StatusEnum.ACTIVE);

        auditService.stampAuditedEntity(mapping);
        accountMappingRepository.save(mapping);


    }

    private TAccount getCommissionAccountToAssign(Long accountId){

        Optional<TAccount> account = accountRepository.findById(accountId);
        Validate.isPresent(account,ErrorMessageConstants.ACCOUNT_WITH_ID_NOT_FOUND,accountId);

        TAccount accountProvided = account.get();

        AccountUtilities.checkThatAccountCanBeAssigned(accountProvided);
        Validate.isTrue(account.get().getAccountType().equals(AccountTypeEnum.COMMISSION),ErrorMessageConstants.COMMISSION_ACCOUNT_REQUIRED);

        return accountProvided;
    }

    @Override
    public void linkAccount(AccountLink link) {
        if (link.getLinkType().equals(AccountLink.LinkType.AGENT)) {
            linkAgentCommissionAccount(link.getEntityId(),link.getAccountId());

        }else if(link.getLinkType().equals(AccountLink.LinkType.SYSTEM)){
            linkSystemCommissionAccount(link.getAccountId());
        }else{
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void unLinkAccount(AccountLink link) {
        if (link.getLinkType().equals(AccountLink.LinkType.AGENT)) {
            unlinkAgentCommissionAccount(link.getEntityId(),link.getAccountId());

        }else if(link.getLinkType().equals(AccountLink.LinkType.SYSTEM)){
            unlinkSystemCommissionAccount(link.getAccountId());
        }else{
            throw new UnsupportedOperationException();
        }
    }


    private void linkSystemCommissionAccount(Long accountId) {

        Optional<TAccountMapping> accountOptional = accountMappingRepository.findSystemCommissionAccountMapped();
        Validate.isTrue(!accountOptional.isPresent(),"System commission account already exists, unmap first to change");

        TAccount accountProvided = getCommissionAccountToAssign(accountId);

        accountProvided.setAssigned(Boolean.TRUE);
        accountProvided.setAccountStatus(AccountStatusEnum.ACTIVE);

        auditService.stampAuditedEntity(accountProvided);
        accountRepository.save(accountProvided);

        TAccountMapping mapping = new TAccountMapping();
        mapping.setAccountId(accountProvided);
        mapping.setStatus(StatusEnum.ACTIVE);
        mapping.setSystemAccount(Boolean.TRUE);

        auditService.stampAuditedEntity(mapping);
        accountMappingRepository.save(mapping);


    }


    private void unlinkAgentCommissionAccount(Long accountId, Long agentId) {

        Optional<TAccountMapping> mappingOptional = accountMappingRepository.findAgentCommissionAccountMapped(agentId);
        Validate.isPresent(mappingOptional,"Agent commission account mapping does not exist");

        TAccountMapping mapping = mappingOptional.get();

        TAccount account = mapping.getAccountId();
        Validate.isTrue(account.getId().equals(accountId),"Account ID in mapped object do not match");

        AccountUtilities.checkThatAccountCanBeUnAssigned(account);

        mapping.setStatus(StatusEnum.NOT_ACTIVE);
        auditService.stampAuditedEntity(mapping);
        accountMappingRepository.save(mapping);

        closeAccount(account);
        auditService.stampAuditedEntity(account);

        accountRepository.save(account);



    }


    private void unlinkSystemCommissionAccount(Long accountId) {

        Optional<TAccountMapping> mappingOptional = accountMappingRepository.findSystemCommissionAccountMapped();
        Validate.isPresent(mappingOptional,"System commission account mapping does not exist");

        TAccountMapping mapping = mappingOptional.get();

        TAccount account = mapping.getAccountId();
        Validate.isTrue(account.getId().equals(accountId),"Account ID in mapped object do not match");

        AccountUtilities.checkThatAccountCanBeUnAssigned(account);

        mapping.setStatus(StatusEnum.NOT_ACTIVE);
        auditService.stampAuditedEntity(mapping);
        accountMappingRepository.save(mapping);

        closeAccount(account);
        auditService.stampAuditedEntity(account);

        accountRepository.save(account);

    }

    private void closeAccount(TAccount account)
    {
        LoggedInUser user = auditService.getLoggedInUser();

        final TUser s = new TUser();
        s.setId(user.getId());

        account.setClosedBy(s);
        account.setClosedOn(DateTimeUtil.getCurrentUTCTime());
        account.setAccountStatus(AccountStatusEnum.CLOSED);
    }

    private void validateAccountingGroup(Account account){
        AccountingGroup group = account.getAccountGrouping();
        if(group != null){
            Validate.notNull(group.getId(),"Account group cannot be null");

            Optional<TAccountGrouping> grouping =  accountGroupingRepository.findById(group.getId());

            Validate.isTrue(grouping.isPresent(),"Account group provided could not be found");
        }
    }

    private String generateAccountCode(AccountTypeEnum accountTypeEnum, Long accountId){
        final String id = String.valueOf(accountId);
        final Integer totalLengthUncovered = ACCOUNT_CODE_LENGTH - id.length();
        return accountTypeEnum.getAcronym()+StringUtil.getSequence("0",totalLengthUncovered)+id;
    }
}
