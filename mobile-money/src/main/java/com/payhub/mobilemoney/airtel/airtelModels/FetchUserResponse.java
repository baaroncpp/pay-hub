package com.payhub.mobilemoney.airtel.airtelModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlType(name = "COMMAND")
public class FetchUserResponse {
    private String type;//<TYPE>GENERAL</TYPE>
	private String grade;//<GRADE>MCOM</GRADE>
    private String walletType;//<WALLETTYPE>12</WALLETTYPE>
    private double balance;//<BALANCE>395</BALANCE>
    private String firstName;//<FIRSTNAME>NAPHLIN</FIRSTNAME>
    private String lastName;//<LASTNAME>AKENA</LASTNAME>
    private String nationality;//<NATIONALITY>UGANDAN</NATIONALITY>
    private Date dod;//<DOB>1988-10-24 16:38:53.0</DOB>
    private String idNumber;//<IDNUMBER>CM880221055K5K</IDNUMBER>
    private String accountStatus;//<ACCOUNTSTATUS>Y</ACCOUNTSTATUS>
    private String idType;//<IDTYPE>NID</IDTYPE>
    private String address;//<ADDRESS>null</ADDRESS>
    private String additionData;//<ADDITIONDATA>NAPHLIN,AKENA,24/10/88,Male</ADDITIONDATA>
    private String regStatus;//<REGSTATUS>MCOM</REGSTATUS>
    private String txnStatusEig;//<TXNSTATUSEIG>200</TXNSTATUSEIG>
    private String msisdn;//<MSISDN>707444219</MSISDN>
    private String message;//<MESSAGE>Success</MESSAGE>
    private int txnStatus;//<TXNSTATUS>200</TXNSTATUS>
    private String date;//<DATE>0122043032</DATE>
    private String time;//<TIME>043032</TIME>
    private String userType;//<USERTYPE>SUBSCRIBER</USERTYPE>
    private String txnId;//TXNID>49577292915</TXNID>
    private boolean isPinSet;//<IS_PIN_SET>true</IS_PIN_SET>
    private boolean isPinReset;// <IS_PIN_RESET>false</IS_PIN_RESET>
    private boolean isUserBarred;//<IS_USER_BARRED>false</IS_USER_BARRED>
    private String barType;//<BAR_TYPE>null</BAR_TYPE>
    private String transactionAllowed;//<TRANSACTIONALLOWED>Y</TRANSACTIONALLOWED>

    public FetchUserResponse() {
    }

    public String getType() {
        return type;
    }

    @XmlElement(name = "TYPE")
    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    @XmlElement(name = "GRADE")
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getWalletType() {
        return walletType;
    }

    @XmlElement(name = "WALLETTYPE")
    public void setWalletType(String walletType) {
        this.walletType = walletType;
    }

    public double getBalance() {
        return balance;
    }

    @XmlElement(name = "BALANCE")
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    @XmlElement(name = "FIRSTNAME")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlElement(name = "LASTNAME")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    @XmlElement(name = "NATIONALITY")
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getDod() {
        return dod;
    }

    @XmlElement(name = "DOB")
    public void setDod(Date dod) {
        this.dod = dod;
    }

    public String getIdNumber() {
        return idNumber;
    }

    @XmlElement(name = "IDNUMBER")
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    @XmlElement(name = "ACCOUNTSTATUS")
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getIdType() {
        return idType;
    }

    @XmlElement(name = "IDTYPE")
    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement(name = "ADDRESS")
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdditionData() {
        return additionData;
    }

    @XmlElement(name = "ADDITIONDATA")
    public void setAdditionData(String additionData) {
        this.additionData = additionData;
    }

    public String getRegStatus() {
        return regStatus;
    }

    @XmlElement(name = "REGSTATUS")
    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public String getTxnStatusEig() {
        return txnStatusEig;
    }

    @XmlElement(name = "TXNSTATUSEIG")
    public void setTxnStatusEig(String txnStatusEig) {
        this.txnStatusEig = txnStatusEig;
    }

    public String getMsisdn() {
        return msisdn;
    }

    @XmlElement(name = "MSISDN")
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getMessage() {
        return message;
    }

    @XmlElement(name = "MESSAGE")
    public void setMessage(String message) {
        this.message = message;
    }

    public int getTxnStatus() {
        return txnStatus;
    }

    @XmlElement(name = "TXNSTATUS")
    public void setTxnStatus(int txnStatus) {
        this.txnStatus = txnStatus;
    }

    public String getDate() {
        return date;
    }

    @XmlElement(name = "DATE")
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    @XmlElement(name = "TIME")
    public void setTime(String time) {
        this.time = time;
    }

    public String getUserType() {
        return userType;
    }

    @XmlElement(name = "USERTYPE")
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getTxnId() {
        return txnId;
    }

    @XmlElement(name = "TXNID")
    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public boolean isPinSet() {
        return isPinSet;
    }

    @XmlElement(name = "IS_PIN_SET")
    public void setPinSet(boolean pinSet) {
        isPinSet = pinSet;
    }

    public boolean isPinReset() {
        return isPinReset;
    }

    @XmlElement(name = "IS_PIN_RESET")
    public void setPinReset(boolean pinReset) {
        isPinReset = pinReset;
    }

    public boolean isUserBarred() {
        return isUserBarred;
    }

    @XmlElement(name = "IS_USER_BARRED")
    public void setUserBarred(boolean userBarred) {
        isUserBarred = userBarred;
    }

    public String getBarType() {
        return barType;
    }

    @XmlElement(name = "BAR_TYPE")
    public void setBarType(String barType) {
        this.barType = barType;
    }

    public String getTransactionAllowed() {
        return transactionAllowed;
    }

    @XmlElement(name = "TRANSACTIONALLOWED")
    public void setTransactionAllowed(String transactionAllowed) {
        this.transactionAllowed = transactionAllowed;
    }
}
