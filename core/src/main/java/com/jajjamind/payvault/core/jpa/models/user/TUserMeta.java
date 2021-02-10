package com.jajjamind.payvault.core.jpa.models.user;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.agent.TTermsAndConditions;
import com.jajjamind.payvault.core.jpa.models.enums.GenderEnum;
import com.jajjamind.payvault.core.jpa.models.agent.TCountry;

import javax.persistence.*;
import java.util.Date;

/**
 * @author akena
 * 13/12/2020
 * 02:54
 **/
@Entity
@Table(name = "t_user_meta",schema = "core")
public class TUserMeta extends AuditedEntity {

    private String firstName;
    private String lastName;
    private String middleName;
    private Long userId;
    private Long agentId;
    private String phoneNumber;
    private String phoneNumber2;
    private String imagePath;
    private String displayName;
    private GenderEnum gender;
    private Date birthDate;
    private String email;
    private TCountry countryCode;
    private String identification;
    private String identificationNumber;
    private String identificationPath;
    private Boolean nonVerifiedEmail;
    private Boolean nonVerifiedPhoneNumber;
    private TTermsAndConditions termsAndConditions;


    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "agent_id")
    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "phone_number_2")
    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    @Column(name = "image_path")
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Column(name = "display_name")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    @Column(name = "birth_date")
    public Date  getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JoinColumn(name = "country_id",referencedColumnName = "id",insertable = true,updatable = true)
    @OneToOne(fetch = FetchType.EAGER)
    public TCountry getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(TCountry countryCode) {
        this.countryCode = countryCode;
    }

    @Column(name = "identification")
    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @Column(name = "identification_number")
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    @Column(name = "identification_path")
    public String getIdentificationPath() {
        return identificationPath;
    }

    public void setIdentificationPath(String identificationPath) {
        this.identificationPath = identificationPath;
    }

    @Column(name = "non_verified_email")
    public boolean isNonVerifiedEmail() {
        return nonVerifiedEmail;
    }

    public void setNonVerifiedEmail(boolean nonVerifiedEmail) {
        this.nonVerifiedEmail = nonVerifiedEmail;
    }

    @Column(name = "non_verified_phone_number")
    public boolean isNonVerifiedPhoneNumber() {
        return nonVerifiedPhoneNumber;
    }

    public void setNonVerifiedPhoneNumber(boolean nonVerifiedPhoneNumber) {
        this.nonVerifiedPhoneNumber = nonVerifiedPhoneNumber;
    }

    @JoinColumn(name = "terms_and_condition_id", referencedColumnName = "id",insertable = true,updatable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    public TTermsAndConditions getTTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTTermsAndConditions(TTermsAndConditions termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }
}
