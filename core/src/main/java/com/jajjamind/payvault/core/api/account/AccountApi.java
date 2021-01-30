package com.jajjamind.payvault.core.api.account;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.account.models.Account;
import com.jajjamind.payvault.core.api.account.models.AccountingGroup;
import com.jajjamind.payvault.core.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author akena
 * 14/01/2021
 * 21:18
 **/
@RestController
@RequestMapping("/accounts")
public class AccountApi implements BaseApi<Account> {

    @Autowired
    public AccountService accountService;

    @Override
    public Account add(@RequestBody  Account account) {
        return
                accountService.addAccount(account);
    }

    @Override
    public Account get(Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/grouping/{id}")
    public List<Account> getAccountByGroupId(Long id) {
        return accountService.getAccountsByGroup(id);
    }

    @Override
    public Account update(@RequestBody  Account account) {
        return null;
    }

    @Override
    public Account delete(long id) {
        return null;
    }

    @Override
    public List<Account> getAll() {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/group")
    public AccountingGroup addAccountingGroup(@RequestBody  AccountingGroup g){
        return accountService.addAccountGrouping(g);
    }

    @GetMapping("/group/{id}")
    public AccountingGroup getGroupId(@PathVariable("id") Long id){
        return accountService.getAccountingGroupById(id);
    }

    @GetMapping("/group")
    public List<AccountingGroup> getAllAccountGroup(){
        return accountService.getAllAccountingGroup();
    }

    @PutMapping("/group")
    public AccountingGroup updateAccountingGroup(@RequestBody  AccountingGroup g){
        return accountService.updateAccountGrouping(g);
    }
}
