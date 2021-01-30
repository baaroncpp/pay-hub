package com.jajjamind.payvault.core.api.bank;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.Approve;
import com.jajjamind.payvault.core.api.bank.models.Bank;
import com.jajjamind.payvault.core.api.bank.models.BankDeposit;
import com.jajjamind.payvault.core.service.bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author akena
 * 11/01/2021
 * 13:59
 **/
@RestController
@RequestMapping("/bank")
public class BankApi implements BaseApi<Bank> {

    @Autowired
    BankService bankService;


    @Override
    public Bank add(@Validated @RequestBody Bank bank) {
        return bankService.add(bank);
    }

    @Override
    public Bank get(Long id) {
        return bankService.getBankById(id.intValue());
    }

    @Override
    public Bank update(Bank bank) {
        return null;
    }

    @Override
    public Bank delete(long id) {
        return null;
    }

    @Override
    public List<Bank> getAll() {
        return bankService.getAll();
    }

    @PostMapping("/deposit")
    public BankDeposit createABankDeposit(){

        return null;
    }

    //Approve should top up main account(Require 2 approvals) -- as configured in settings
    @PostMapping("/deposit/approve")
    public BankDeposit approveDeposit(@RequestBody  Approve approveRequest){
        return null;
    }
}
