package com.jajjamind.payvault.core.service.account;

import com.jajjamind.commons.text.StringUtil;
import com.jajjamind.commons.time.DateTimeUtil;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.account.models.Account;
import com.jajjamind.payvault.core.api.account.models.AccountingGroup;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.jpa.models.account.TAccount;
import com.jajjamind.payvault.core.jpa.models.account.TAccountGrouping;
import com.jajjamind.payvault.core.jpa.models.enums.AccountStatusEnum;
import com.jajjamind.payvault.core.jpa.models.enums.AccountTypeEnum;
import com.jajjamind.payvault.core.jpa.models.user.TUser;
import com.jajjamind.payvault.core.repository.JooqFilter;
import com.jajjamind.payvault.core.repository.account.AccountGroupingRepository;
import com.jajjamind.payvault.core.repository.account.AccountRepository;
import com.jajjamind.payvault.core.repository.account.JooqAccountRepository;
import com.jajjamind.payvault.core.security.models.LoggedInUser;
import com.jajjamind.payvault.core.utils.AuditService;
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

    private static final Integer ACCOUNT_CODE_LENGTH = 6;

    @Override
    public Account addAccount(Account account) {

        account.validate();
        validateAccountingGroup(account);

        TAccount tAccount = new TAccount();
        BeanUtils.copyProperties(account,tAccount);

        TAccountGrouping grouping = null;

        if(account.getAccountGrouping() != null) {
            grouping = new TAccountGrouping();
            grouping.setId(account.getAccountGrouping().getId());
        }

        tAccount.setAccountGrouping(grouping);
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

        BeanUtils.copyProperties(createdAccount.get(),account);
        account.setCode(ca.getCode());

        return account;
    }

    @Override
    public AccountingGroup addAccountGrouping(AccountingGroup accountGroup) {

        accountGroup.validate();
        TAccountGrouping group = new TAccountGrouping();
        BeanUtils.copyProperties(accountGroup,group);

        auditService.stampAuditedEntity(group);
        accountGroupingRepository.save(group);

        Optional<TAccountGrouping> created = accountGroupingRepository.findByName(accountGroup.getName());
        Validate.isTrue(created.isPresent(),"Failed to create accounting group");

        BeanUtils.copyProperties(created.get(),accountGroup);
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

        Validate.isTrue(!account.getAccountStatus().equals(AccountStatusEnum.NOT_ACTIVE),"Account is already deactivated");

        account.setAccountStatus(AccountStatusEnum.ACTIVE);

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

        account.setAccountStatus(AccountStatusEnum.CLOSED);

        Date date = DateTimeUtil.getCurrentUTCTime();
        LoggedInUser user = auditService.getLoggedInUser();

        final TUser s = new TUser();
        s.setId(user.getId());

        account.setClosedBy(s);
        account.setClosedOn(date);

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
            BeanUtils.copyProperties(t,g);
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
