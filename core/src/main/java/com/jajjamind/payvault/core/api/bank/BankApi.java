package com.jajjamind.payvault.core.api.bank;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.Approve;
import com.jajjamind.payvault.core.api.bank.models.Bank;
import com.jajjamind.payvault.core.api.bank.models.BankDeposit;
import com.jajjamind.payvault.core.api.users.models.Approval;
import com.jajjamind.payvault.core.service.bank.BankDepositService;
import com.jajjamind.payvault.core.service.bank.BankService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author akena
 * 11/01/2021
 * 13:59
 **/
@Tag(name = "Bank",description = "Manage bank operations for PayHub")
@RestController
@RequestMapping("/bank")
public class BankApi implements BaseApi<Bank> {

    @Autowired
    BankService bankService;

    @Autowired
    BankDepositService bankDepositService;

    @RolesAllowed("ROLE_BANK.WRITE")
    @Override
    public Bank add(@Validated @RequestBody Bank bank) {
        return bankService.add(bank);
    }

    @RolesAllowed({"ROLE_BANK.WRITE","ROLE_BANK.READ"})
    @Override
    public Bank get(@PathVariable("id") Long id) {
        return bankService.getBankById(id.intValue());
    }

    @RolesAllowed("ROLE_BANK.WRITE")
    @Override
    public Bank update(@RequestBody Bank bank) {
        return bankService.updateBank(bank);
    }

    @Override
    public Bank delete(long id) {
        throw new NotImplementedException();
    }

    @RolesAllowed({"ROLE_BANK.WRITE","ROLE_BANK.READ"})
    @Override
    public List<Bank> getAll() {
        return bankService.getAll();
    }

    @RolesAllowed({"ROLE_BANK_DEPOSIT.WRITE"})
    @PostMapping("/deposit/initiate")
    public BankDeposit createABankDeposit(@RequestBody  BankDeposit deposit){

        return bankDepositService.initiateDeposit(deposit);
    }

    //Approve should top up main account(Require 2 approvals) -- as configured in settings
    @RolesAllowed("ROLE_BANK_DEPOSIT.CHECKER")
    @PostMapping("/deposit/approve")
    public void approveDeposit(@RequestBody Approval approveRequest){
        bankDepositService.approveBankDeposit(approveRequest);
    }

    @RolesAllowed({"ROLE_BANK_ACCOUNT.LINK"})
    @PostMapping("/account/link")
    public void linkAccountToBank(@RequestParam("accountId") Long accountId,@RequestParam("bankId") Integer bankId){
        bankDepositService.linkMainAccountToBank(accountId,bankId);
    }

    @RolesAllowed({"ROLE_BANK_ACCOUNT.UNLINK"})
    @PostMapping("/account/unlink")
    public void unlinkAccountFromBank(@RequestParam("accountId") Long accountId,@RequestParam("bankId") Integer bankId){
        bankDepositService.unlinkMainAccountFromBank(accountId,bankId);
    }
}
