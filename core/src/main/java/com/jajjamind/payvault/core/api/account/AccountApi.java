package com.jajjamind.payvault.core.api.account;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.account.models.Account;
import com.jajjamind.payvault.core.api.account.models.AccountingGroup;
import com.jajjamind.payvault.core.repository.account.JooqAccountRepository;
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
public class AccountApi {

    @Autowired
    public AccountService accountService;

    @PostMapping(consumes = BaseApi.APPLICATION_JSON,produces = BaseApi.APPLICATION_JSON)
    public Account add(@RequestBody  Account account) {
        return
                accountService.addAccount(account);
    }

    @GetMapping(value = "/{id}",produces = BaseApi.APPLICATION_JSON)
    public JooqAccountRepository.Result get(@PathVariable("id") Long id) {
        return  accountService.getAccountById(id);
    }

    @GetMapping("/grouping/{id}")
    public List<Account> getAccountByGroupId(@PathVariable("id") Long id) {
        return accountService.getAccountsByGroup(id);
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
