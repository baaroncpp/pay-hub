package com.jajjamind.payvault.core.jpa.models.bank;

import com.jajjamind.payvault.core.jpa.models.BaseEntityInteger;
import com.jajjamind.payvault.core.jpa.models.agent.TCountry;

import javax.persistence.*;

/**
 * @author akena
 * 16/12/2020
 * 00:35
 **/
@Table(name = "t_bank_account",schema = "core")
@Entity
public class TBankAccount extends BaseEntityInteger {

    private String bankName;
    private String accountName;
    private String accountNumber;
    private String branch;
    private String swiftCode;
    private String currency;
    private TCountry country;

    @Column(name = "bank_name")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(name = "account_name")
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Column(name = "account_number")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "branch")
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Column(name = "swift_code")
    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JoinColumn(name = "country_id",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TCountry getCountry() {
        return country;
    }

    public void setCountry(TCountry country) {
        this.country = country;
    }
}
