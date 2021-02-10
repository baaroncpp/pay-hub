package com.jajjamind.payvault.core.api.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jajjamind.commons.utils.Validate;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author akena
 * 18/01/2021
 * 03:38
 **/
@lombok.Data
@JsonIgnoreProperties( ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Long id;
    private String username;
    private String newPassword;
    private Boolean accountLocked;
    private Boolean accountExpired;
    private Boolean credentialExpired;
    private TermsAndConditions termsOfUse;
    private UserAuthority userAuthority;
    private UserMeta userMeta;

    public void validate(){
        Validate.notEmpty(username,"Username cannot be empty");

        validatePassword(this.newPassword);
        Validate.notNull(termsOfUse,"User must accept the terms of service while being registered");
        Validate.notNull(termsOfUse.getId(),"Invalid terms of service provided");
        this.userMeta.validate();
    }

    public void validatePassword(String password){
        Validate.notEmpty(password,"Password cannot be empty");
        final Predicate<String> rule1 = s -> s.length() >= 6 && s.length() <= 50;
        final Predicate<String> rule2a = s -> !s.equals(s.toLowerCase());
        final Predicate<String> rule2b = s -> !s.equals(s.toUpperCase());
        final Predicate<String> rule2c = s -> s.codePoints().anyMatch(Character::isDigit);
        final Predicate<String> rule2d = s -> s.codePoints().anyMatch(i -> !Character.isAlphabetic(i));
        final Predicate<String> rule2 = s -> Stream.of(rule2a, rule2b, rule2c, rule2d)
                .filter(p -> p.test(s))
                .count() >= 3;

        Validate.isTrue(rule1.and(rule2).test(password),
                "Password violates password rule. Make sure password is atleast 6 characters in lenght, has lower case and uppercase letters and is alphanumeric" );

    }
}
