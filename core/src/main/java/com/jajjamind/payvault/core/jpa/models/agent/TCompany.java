package com.jajjamind.payvault.core.jpa.models.agent;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.enums.IdentificationEnum;

import javax.persistence.*;

/**
 * @author akena
 * 13/12/2020
 * 02:34
 **/
@Entity
@Table(name = "t_company",schema = "core")
public class TCompany extends AuditedEntity {

    private String businessName;
    private String natureOfBusiness;
    private String physicalAddress;
    private String phoneNumber;
    private TDistrict district;
    private String tinNumber;
    private TCountry registrationCountry;
    private String contactPerson;
    private IdentificationEnum contactIdentification;
    private String contactIdentificationNumber;
    private String contactIdentificationPath;
    private String contactPhoneNumber;
    private String email;
    private String formSerial;
    private String note;

    @Column(name = "business_name")
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Column(name = "nature_of_business")
    public String getNatureOfBusiness() {
        return natureOfBusiness;
    }

    public void setNatureOfBusiness(String natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
    }

    @Column(name = "physical_address")
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JoinColumn(name = "district",referencedColumnName = "id",insertable = true,updatable = true)
    @OneToOne(fetch = FetchType.EAGER)
    public TDistrict getDistrict() {
        return district;
    }

    public void setDistrict(TDistrict district) {
        this.district = district;
    }

    @Column(name = "tin_number")
    public String getTinNumber() {
        return tinNumber;
    }

    public void setTinNumber(String tinNumber) {
        this.tinNumber = tinNumber;
    }

    @JoinColumn(name = "registration_country",referencedColumnName = "id",insertable = true,updatable = true)
    @OneToOne(fetch = FetchType.EAGER)
    public TCountry getRegistrationCountry() {
        return registrationCountry;
    }

    public void setRegistrationCountry(TCountry registrationCountry) {
        this.registrationCountry = registrationCountry;
    }

    @Column(name = "contact_person")
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Column(name = "contact_identification")
    @Enumerated(EnumType.STRING)
    public IdentificationEnum getContactIdentification() {
        return contactIdentification;
    }

    public void setContactIdentification(IdentificationEnum contactIdentification) {
        this.contactIdentification = contactIdentification;
    }

    @Column(name = "contact_identification_number")
    public String getContactIdentificationNumber() {
        return contactIdentificationNumber;
    }

    public void setContactIdentificationNumber(String contactIdentificationNumber) {
        this.contactIdentificationNumber = contactIdentificationNumber;
    }

    @Column(name = "contact_identification_path")
    public String getContactIdentificationPath() {
        return contactIdentificationPath;
    }

    public void setContactIdentificationPath(String contactIdentificationPath) {
        this.contactIdentificationPath = contactIdentificationPath;
    }

    @Column(name = "contact_phone_number")
    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "pv_registration_serial")
    public String getFormSerial() {
        return formSerial;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setFormSerial(String formSerial) {
        this.formSerial = formSerial;
    }
}
