package com.jajjamind.payvault.core.service.bank;

import com.jajjamind.payvault.core.api.bank.models.Bank;

import java.util.List;

/**
 * @author akena
 * 11/01/2021
 * 15:29
 **/
public interface BankService {

    Bank add(Bank bank);
    Bank getBankById(Integer id);
    Bank getBanksByCountry(String countryCode);
    List<Bank> getAll();
    Bank updateBank(Bank bank);

}
