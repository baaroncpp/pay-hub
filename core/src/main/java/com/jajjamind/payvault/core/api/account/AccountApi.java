package com.jajjamind.payvault.core.api.account;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.account.models.Account;
import com.jajjamind.payvault.core.api.account.models.AccountLink;
import com.jajjamind.payvault.core.api.account.models.AccountingGroup;
import com.jajjamind.payvault.core.jpa.models.RecordList;
import com.jajjamind.payvault.core.repository.account.JooqAccountRepository;
import com.jajjamind.payvault.core.service.account.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author akena
 * 14/01/2021
 * 21:18
 **/
@Tag(name = "Accounts",description = "Manage all accounts (transactional, business, main(bank) and partner)")
@RestController
@RequestMapping("/accounts")
public class AccountApi {

    @Autowired
    public AccountService accountService;

    @RolesAllowed("ROLE_ACCOUNT.WRITE")
    @PostMapping(consumes = BaseApi.APPLICATION_JSON,produces = BaseApi.APPLICATION_JSON)
    public Account add(@RequestBody  Account account) {
        return
                accountService.addAccount(account);
    }

    @RolesAllowed("ROLE_ACCOUNT.READ")
    @GetMapping(value = "/{id}",produces = BaseApi.APPLICATION_JSON)
    public JooqAccountRepository.Result get(@PathVariable("id") Long id) {
        return  accountService.getAccountById(id);
    }

    @RolesAllowed({"ROLE_ACCOUNT.READ","ROLE_ACCOUNT.WRITE"})
    @GetMapping(value = "/query",produces = BaseApi.APPLICATION_JSON)
    public RecordList getAllAccounts(@RequestParam MultiValueMap map){
        return  accountService.getAllAccounts(map);
    }

    @RolesAllowed("ROLE_ACCOUNT.READ")
    @GetMapping(value = "/code/{code}",produces = BaseApi.APPLICATION_JSON)
    public Account getByCode(@PathVariable("code") String id) {
        return  accountService.getAccountByCode(id);
    }

    @RolesAllowed("ROLE_ACCOUNT.DEACTIVATE")
    @PostMapping(value = "/deactivate/{id}")
    public void deactivateAccount(@PathVariable("id") Long id) {
          accountService.deactivateAccount(id);
    }

    @RolesAllowed("ROLE_ACCOUNT.ACTIVATE")
    @PostMapping(value = "/activate/{id}")
    public void activate(@PathVariable("id") Long id) {
        accountService.activateAccount(id);
    }

    @RolesAllowed("ROLE_ACCOUNT.CLOSE")
    @PostMapping(value = "/close/{id}")
    public void close(@PathVariable("id") Long id) {
        accountService.closeAccount(id);
    }

    @RolesAllowed("ROLE_ACCOUNT.LINK")
    @PostMapping(value = "/commission/link")
    public void linkAccount(@RequestBody AccountLink link) {
        accountService.linkAccount(link);
    }


    @RolesAllowed("ROLE_ACCOUNT.UNLINK")
    @PostMapping(value = "/commission/un-link")
    public void unlinkAccount(@RequestBody AccountLink link) {
        accountService.unLinkAccount(link);
    }


    @RolesAllowed("ROLE_ACCOUNT.READ")
    @GetMapping("/grouping/{id}")
    public List<Account> getAccountByGroupId(@PathVariable("id") Long id) {
        return accountService.getAccountsByGroup(id);
    }

    @RolesAllowed("ROLE_ACCOUNT.WRITE")
    @PostMapping("/group")
    public AccountingGroup addAccountingGroup(@RequestBody  AccountingGroup g){
        return accountService.addAccountGrouping(g);
    }

    @RolesAllowed("ROLE_ACCOUNT.READ")
    @GetMapping("/group/{id}")
    public AccountingGroup getGroupId(@PathVariable("id") Long id){
        return accountService.getAccountingGroupById(id);
    }

    @RolesAllowed("ROLE_ACCOUNT.READ")
    @GetMapping("/group")
    public List<AccountingGroup> getAllAccountGroup(){
        return accountService.getAllAccountingGroup();
    }

    @RolesAllowed("ROLE_ACCOUNT.WRITE")
    @PutMapping("/group")
    public AccountingGroup updateAccountingGroup(@RequestBody  AccountingGroup g){
        return accountService.updateAccountGrouping(g);
    }
}
