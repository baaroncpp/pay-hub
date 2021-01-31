package com.jajjamind.payvault.core.service.bank;

import com.jajjamind.payvault.core.api.agent.models.Country;
import com.jajjamind.payvault.core.api.bank.models.Bank;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.jpa.models.agent.TCountry;
import com.jajjamind.payvault.core.jpa.models.bank.TBankAccount;
import com.jajjamind.payvault.core.jpa.models.enums.CurrencyEnum;
import com.jajjamind.payvault.core.repository.agent.CountryRepository;
import com.jajjamind.payvault.core.repository.bank.BankAccountRepository;
import com.jajjamind.payvault.core.utils.AuditService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jajjamind.commons.utils.Validate;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author akena
 * 11/01/2021
 * 15:30
 **/
@Service
public class BankServiceImpl implements BankService{

    @Autowired
    public BankAccountRepository  bankAccountRepository;

    @Autowired
    public CountryRepository countryRepository;

    @Autowired
    public AuditService auditService;

    @Override
    public Bank add(Bank bank) {

        Validate.notNull(bank,ErrorMessageConstants.BANK_PAYLOAD_CANNOT_BE_NULL);
        Validate.notNull(bank.getCountry().getIsoAlpha2(),"Country of bank is required");

        Optional<TCountry> country = countryRepository.findByAlpha2Code(bank.getCountry().getIsoAlpha2());
        Validate.isTrue(country.isPresent(),"Country code %s is not supported",bank.getCountry().getIsoAlpha2());

        CurrencyEnum currency = CurrencyEnum.valueOf(bank.getCurrency());
        Validate.notNull(currency,"Currency %s is not supported",bank.getCurrency());

        final TBankAccount bankAccount = new TBankAccount();
        BeanUtils.copyProperties(bank,bankAccount);
        bankAccount.setCountry(country.get());
        auditService.stampIntegerEntity(bankAccount);

        bankAccountRepository.save(bankAccount);

        Optional<TBankAccount> account = bankAccountRepository.findByAccountNumber(bankAccount.getAccountNumber());
        Validate.isTrue(account.isPresent(),"Failed to create entry for bank account with account number %s",bank.getAccountName());

        BeanUtils.copyProperties(account.get(),bank);
        return bank;
    }

    @Override
    public Bank getBankById(Integer id) {

        Optional<TBankAccount> bankAccount = bankAccountRepository.findById(id);

        Validate.isTrue(bankAccount.isPresent(), ErrorMessageConstants.BANK_ACCOUNT_NOT_FOUND,String.valueOf(id));

       return getBankAccount(bankAccount.get());
    }

    @Override
    public Bank getBanksByCountry(String countryCode) {
        return null;
    }

    @Override
    public List<Bank> getAll() {
        Iterable<TBankAccount> bankAccounts = bankAccountRepository.findAll();

        final List<Bank> listOfBanks = new ArrayList<>();
        bankAccounts.forEach(t -> listOfBanks.add(getBankAccount(t)));

        return listOfBanks;
    }


    @Override
    public Bank updateBank(Bank bank) {
        Validate.notNull(bank,ErrorMessageConstants.BANK_PAYLOAD_CANNOT_BE_NULL );
        Validate.notNull(bank.getId(),"A valid bank account ID is required");

        Optional<TBankAccount> bankAccount = bankAccountRepository.findById(bank.getId());

        Validate.isTrue(bankAccount.isPresent(),"Failed to locate bank account with id %s for update",String.valueOf(bank.getId()));

        TBankAccount account = bankAccount.get();
        BeanUtils.copyProperties(bank,bankAccount);
        auditService.stampIntegerEntity(account);

        bankAccountRepository.save(account);
        return bank;
    }

    private Bank getBankAccount(TBankAccount bankAccount){

        Bank bank = new Bank();
        BeanUtils.copyProperties(bankAccount,bank);

        if(bankAccount.getCountry() != null){
            Country country = new Country();
            BeanUtils.copyProperties(bankAccount.getCountry(),country);
            bank.setCountry(country);
        }

        return bank;
    }

}
