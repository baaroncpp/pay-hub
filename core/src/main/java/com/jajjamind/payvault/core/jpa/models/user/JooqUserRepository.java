package com.jajjamind.payvault.core.jpa.models.user;

/**
 * @author akena
 * 10/02/2021
 * 00:31
 **/

//region Begin Code
/*
This file has been generated by Jooqify.groovy [Wed Feb 10 00:31:24 EAT 2021]
*/
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.payvault.core.jpa.models.RecordList;
import com.jajjamind.payvault.core.repository.JooqFilter;
import com.jajjamind.payvault.core.repository.QueryResultPicker;
import groovy.lang.Tuple2;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.Date;
import java.util.List;

import static com.jajjamind.payvault.core.repository.DbField.dbField;


/*
select
u.first_name as firstName_string,
u.last_name as lastName_string,
u.middle_name as middleName_string,
u.phone_number as phoneNumber_string,
u.phone_number_2 as phoneNumber2_string,
u.image_path as imagePath_string,
u.display_name as displayName_string,
to_char( u.birth_date,'DD-MON-YYYY') as birthDate_date,
u.gender as gender_string,
u.email as email_string,
case when u.non_verified_email
	then 'YES'
	else 'NO'
end as nonVerifiedEmail_string,
case when u.non_verified_phone_number
	then 'YES'
	else 'NO'
end as nonVerifiedPhone_string,
u.terms_and_condition_id as termsAndConditionsId,
to_char( u.modified_on,'DD-MON-YYYY') as modifiedOn_date,
to_char(u.created_on, 'DD-MON-YYYY') as createdOn_date,
case when k.initial_password_reset
	then 'YES'
	else 'NO'
end as initialPasswordReset_string,
case when k.account_locked
 	then 'YES'
	else 'NO'
end as accountLocked_string,
case when k.account_expired
	then 'YES'
	else 'NO'
end as  accountExpired_string,
case when k.cred_expired
	then 'YES'
	else 'NO'
end as  credentialExpired_string,
case when k.approved
	then 'YES'
	else 'NO'
end as approved_string,
case
  when m.middle_name is null then concat(m.first_name, ' ', m.last_name)
  else concat(m.first_name, ' ', m.middle_name, ' ',m.last_name)
end as modifiedBy_string,
case
  when c.middle_name is null then concat(c.first_name, ' ', c.last_name)
  else concat(c.first_name, ' ', c.middle_name, ' ',c.last_name)
end as createdBy_string,

case
  when a.middle_name is null then concat(a.first_name, ' ', a.last_name)
  else concat(a.first_name, ' ', a.middle_name, ' ',a.last_name)
end as approvedBy_string

from core.t_user_meta u
inner join core.t_user k on u.user_id = k.id
left join core.t_user_meta m on m.user_id = u.modified_by
left join core.t_user_meta c on c.user_id = u.created_by
left join core.t_user_meta a on a.user_id = k.approved_by ;
*/
@Service
public class JooqUserRepository implements QueryResultPicker{

    @Autowired
    public DSLContext context;

    public static final String FIELD_FIRST_NAME = "firstName";
    public static final String FIELD_LAST_NAME = "lastName";
    public static final String FIELD_MIDDLE_NAME = "middleName";
    public static final String FIELD_PHONE_NUMBER = "phoneNumber";
    public static final String FIELD_PHONE_NUMBER2 = "phoneNumber2";
    public static final String FIELD_IMAGE_PATH = "imagePath";
    public static final String FIELD_DISPLAY_NAME = "displayName";
    public static final String FIELD_BIRTH_DATE = "birthDate";
    public static final String FIELD_GENDER = "gender";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_NON_VERIFIED_EMAIL = "nonVerifiedEmail";
    public static final String FIELD_NON_VERIFIED_PHONE = "nonVerifiedPhone";
    public static final String FIELD_TERMS_AND_CONDITIONS_ID = "termsAndConditionsId";
    public static final String FIELD_MODIFIED_ON = "modifiedOn";
    public static final String FIELD_CREATED_ON = "createdOn";
    public static final String FIELD_INITIAL_PASSWORD_RESET = "initialPasswordReset";
    public static final String FIELD_ACCOUNT_LOCKED = "accountLocked";
    public static final String FIELD_ACCOUNT_EXPIRED = "accountExpired";
    public static final String FIELD_CREDENTIAL_EXPIRED = "credentialExpired";
    public static final String FIELD_APPROVED = "approved";
    public static final String FIELD_MODIFIED_BY = "modifiedBy";
    public static final String FIELD_CREATED_BY = "createdBy";
    public static final String FIELD_APPROVED_BY = "approvedBy";

    private final Field<String> firstName = DSL.field("u.first_name", String.class);
    private final Field<String> lastName = DSL.field("u.last_name", String.class);
    private final Field<String> middleName = DSL.field("u.middle_name", String.class);
    private final Field<String> phoneNumber = DSL.field("u.phone_number", String.class);
    private final Field<String> phoneNumber2 = DSL.field("u.phone_number_2", String.class);
    private final Field<String> imagePath = DSL.field("u.image_path", String.class);
    private final Field<String> displayName = DSL.field("u.display_name", String.class);
    private final Field<Date> birthDate = DSL.field("to_char(u.birth_date, 'DD-MON-YYYY')", Date.class);
    private final Field<String> gender = DSL.field("u.gender", String.class);
    private final Field<String> email = DSL.field("u.email", String.class);
    private final Field<String> nonVerifiedEmail = DSL.field("CASE WHEN u.non_verified_email THEN 'YES' ELSE 'NO' END", String.class);
    private final Field<String> nonVerifiedPhone = DSL.field("CASE WHEN u.non_verified_phone_number THEN 'YES' ELSE 'NO' END", String.class);
    private final Field<Object> termsAndConditionsId = DSL.field("u.terms_and_condition_id", Object.class);
    private final Field<Date> modifiedOn = DSL.field("to_char(u.modified_on, 'DD-MON-YYYY')", Date.class);
    private final Field<Date> createdOn = DSL.field("to_char(u.created_on, 'DD-MON-YYYY')", Date.class);
    private final Field<String> initialPasswordReset = DSL.field("CASE WHEN k.initial_password_reset THEN 'YES' ELSE 'NO' END", String.class);
    private final Field<String> accountLocked = DSL.field("CASE WHEN k.account_locked THEN 'YES' ELSE 'NO' END", String.class);
    private final Field<String> accountExpired = DSL.field("CASE WHEN k.account_expired THEN 'YES' ELSE 'NO' END", String.class);
    private final Field<String> credentialExpired = DSL.field("CASE WHEN k.cred_expired THEN 'YES' ELSE 'NO' END", String.class);
    private final Field<String> approved = DSL.field("CASE WHEN k.approved THEN 'YES' ELSE 'NO' END", String.class);
    private final Field<String> modifiedBy = DSL.field("CASE WHEN m.middle_name IS NULL THEN concat(m.first_name, ' ', m.last_name) ELSE concat(m.first_name, ' ', m.middle_name, ' ', m.last_name) END", String.class);
    private final Field<String> createdBy = DSL.field("CASE WHEN c.middle_name IS NULL THEN concat(c.first_name, ' ', c.last_name) ELSE concat(c.first_name, ' ', c.middle_name, ' ', c.last_name) END", String.class);
    private final Field<String> approvedBy = DSL.field("CASE WHEN a.middle_name IS NULL THEN concat(a.first_name, ' ', a.last_name) ELSE concat(a.first_name, ' ', a.middle_name, ' ', a.last_name) END", String.class);

    private final Table<?> coreTable = DSL.table("core.t_user_meta u\n"
            + "INNER JOIN core.t_user k ON u.user_id = k.id\n"
            + "LEFT JOIN core.t_user_meta m ON m.user_id = u.modified_by\n"
            + "LEFT JOIN core.t_user_meta c ON c.user_id = u.created_by\n"
            + "LEFT JOIN core.t_user_meta a ON a.user_id = k.approved_by\n"
    );

    private JooqFilter jooqFilter;

    public JooqUserRepository(){
        initFilterer();
    }

    public Select<Record> query(MultiValueMap params){
        final Tuple2<Integer, Integer> limitAndOffset = jooqFilter.paginationParams(params);
        return DSL.select(jooqFilter.selectFields(params))
                .from(coreTable)
                .where(
                        jooqFilter.whereClause(params))
                .orderBy(jooqFilter.sortFields(params))
                .limit(limitAndOffset.getFirst(), limitAndOffset.getSecond());
    }

    private void initFilterer(){
        jooqFilter = new JooqFilter()
                .fields(
                        dbField(firstName, FIELD_FIRST_NAME),
                        dbField(lastName, FIELD_LAST_NAME),
                        dbField(middleName, FIELD_MIDDLE_NAME),
                        dbField(phoneNumber, FIELD_PHONE_NUMBER),
                        dbField(phoneNumber2, FIELD_PHONE_NUMBER2),
                        dbField(imagePath, FIELD_IMAGE_PATH),
                        dbField(displayName, FIELD_DISPLAY_NAME),
                        dbField(birthDate, FIELD_BIRTH_DATE),
                        dbField(gender, FIELD_GENDER),
                        dbField(email, FIELD_EMAIL),
                        dbField(nonVerifiedEmail, FIELD_NON_VERIFIED_EMAIL),
                        dbField(nonVerifiedPhone, FIELD_NON_VERIFIED_PHONE),
                        dbField(termsAndConditionsId, FIELD_TERMS_AND_CONDITIONS_ID),
                        dbField(modifiedOn, FIELD_MODIFIED_ON),
                        dbField(createdOn, FIELD_CREATED_ON),
                        dbField(initialPasswordReset, FIELD_INITIAL_PASSWORD_RESET),
                        dbField(accountLocked, FIELD_ACCOUNT_LOCKED),
                        dbField(accountExpired, FIELD_ACCOUNT_EXPIRED),
                        dbField(credentialExpired, FIELD_CREDENTIAL_EXPIRED),
                        dbField(approved, FIELD_APPROVED),
                        dbField(modifiedBy, FIELD_MODIFIED_BY),
                        dbField(createdBy, FIELD_CREATED_BY),
                        dbField(approvedBy, FIELD_APPROVED_BY)
                );
    }

    @lombok.Data
    @lombok.NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Result{
        private String firstName;
        private String lastName;
        private String middleName;
        private String phoneNumber;
        private String phoneNumber2;
        private String imagePath;
        private String displayName;
        private Date birthDate;
        private String gender;
        private String email;
        private String nonVerifiedEmail;
        private String nonVerifiedPhone;
        private Object termsAndConditionsId;
        private Date modifiedOn;
        private Date createdOn;
        private String initialPasswordReset;
        private String accountLocked;
        private String accountExpired;
        private String credentialExpired;
        private String approved;
        private String modifiedBy;
        private String createdBy;
        private String approvedBy;


    }

    public RecordList<Result> listAndCount(MultiValueMap<String,?> map){
        return new RecordList<Result>(count(map),list(map));
    }

    public List<Result> list(MultiValueMap<String,?> map){
        return context.fetch(this.query(map)).into(Result.class);
    }

    public Long count(MultiValueMap<String,?> map){
        return context.fetchOne(this.query(addCountParams(map))).getValue(0,Long.class);
    }

    public String getAllFields(){
        final StringBuilder builder = new StringBuilder();
        builder.append(FIELD_FIRST_NAME);
        builder.append(",");
        builder.append(FIELD_LAST_NAME);
        builder.append(",");
        builder.append(FIELD_MIDDLE_NAME);
        builder.append(",");
        builder.append(FIELD_PHONE_NUMBER);
        builder.append(",");
        builder.append(FIELD_PHONE_NUMBER2);
        builder.append(",");
        builder.append(FIELD_IMAGE_PATH);
        builder.append(",");
        builder.append(FIELD_DISPLAY_NAME);
        builder.append(",");
        builder.append(FIELD_BIRTH_DATE);
        builder.append(",");
        builder.append(FIELD_GENDER);
        builder.append(",");
        builder.append(FIELD_EMAIL);
        builder.append(",");
        builder.append(FIELD_NON_VERIFIED_EMAIL);
        builder.append(",");
        builder.append(FIELD_NON_VERIFIED_PHONE);
        builder.append(",");
        builder.append(FIELD_TERMS_AND_CONDITIONS_ID);
        builder.append(",");
        builder.append(FIELD_MODIFIED_ON);
        builder.append(",");
        builder.append(FIELD_CREATED_ON);
        builder.append(",");
        builder.append(FIELD_INITIAL_PASSWORD_RESET);
        builder.append(",");
        builder.append(FIELD_ACCOUNT_LOCKED);
        builder.append(",");
        builder.append(FIELD_ACCOUNT_EXPIRED);
        builder.append(",");
        builder.append(FIELD_CREDENTIAL_EXPIRED);
        builder.append(",");
        builder.append(FIELD_APPROVED);
        builder.append(",");
        builder.append(FIELD_MODIFIED_BY);
        builder.append(",");
        builder.append(FIELD_CREATED_BY);
        builder.append(",");
        builder.append(FIELD_APPROVED_BY);
        return builder.toString();
    }

}

//endregion
