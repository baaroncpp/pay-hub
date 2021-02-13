package com.jajjamind.payvault.core.service.account;

import com.jajjamind.payvault.core.api.account.models.Account;
import com.jajjamind.payvault.core.api.account.models.AccountLink;
import com.jajjamind.payvault.core.api.account.models.AccountingGroup;
import com.jajjamind.payvault.core.repository.account.JooqAccountRepository;

import java.util.List;

/**
 * @author akena
 * 14/01/2021
 * 21:44
 **/
public interface AccountService {

    Account addAccount(Account account);
    AccountingGroup addAccountGrouping(AccountingGroup accountGroup);
    AccountingGroup updateAccountGrouping(AccountingGroup accountGroup);
    JooqAccountRepository.Result getAccountById(Long id);
    Account getAccountByCode(String code);
    Account updateAccount(Account account);
    Boolean deactivateAccount(Long accountId);
    Boolean activateAccount(Long accountId);
    Boolean closeAccount(Long accountId);
    List<AccountingGroup> getAllAccountingGroup();
    AccountingGroup getAccountingGroupById(Long id);
    List<Account> getAccountsByGroup(Long id);
    void linkAccount(AccountLink linkType);
    void unLinkAccount(AccountLink linkType);
}
