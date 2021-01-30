package com.jajjamind.payvault.core.repository.agent;


//region Begin Code
/*
This file has been generated by Jooqify.groovy [Sat Jan 30 01:51:48 EAT 2021]
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
	a.id as id_long,
	m.first_name as firstName_string,
	m.middle_name as middleName_string,
	m.last_name as lastName_string,
	case
		when m.middle_name is null then concat(m.first_name, ' ', m.last_name)
		else concat(m.first_name, ' ', m.middle_name, ' ', m.last_name)
	end as fullName_string,
	m.phone_number as phoneNumber1_string,
	m.phone_number_2 as phoneNumber2_string,
	m.image_path as imagePath_string,
	m.gender as gender_String,
	m.birth_date as birthDate_date,
	m.email as email_string,
	m.identification as identification_string,
	m.identification_number as identificationNumber_string,
	m.identification_path as identificationPath_string,
	a.type as type_string,
	r.name as country_string,
	case
		when k.middle_name is null then concat(k.first_name, ' ', k.last_name)
		else concat(k.first_name, ' ', k.middle_name, ' ', k.last_name)
	end as approvedBy_string,
	a.external_id as externalId_string,
	a.activated_on as activatedOn_date,
	a.last_reactivated_on as reactivatedOn_date,
	a.non_locked as nonLocked_boolean,
	a.non_disabled as nonDisabled_boolean,
	a.non_locked_pin as nonLockedPin_boolean,
	a.pin_last_updated_on as pinLastUpdatedOn_date,
	a.last_pin_lock_reason as lasPinLockReason_string,
	a.enrolled_by as enrolledBy_string,
	a.created_on as createdOn_date,
	case
		when p.middle_name is null then concat(p.first_name, ' ', p.last_name)
		else concat(p.first_name, ' ', p.middle_name, ' ', p.last_name)
	end as createdBy_string,
	case
		when t.last_name is null then concat(t.first_name, ' ', t.last_name)
		else concat(t.first_name, ' ', t.middle_name, ' ', t.last_name)
	end as modifiedBy,
	a.modified_on as modifiedOn_date
from
	core.t_agent a
inner join core.t_user_meta m on
	a.id = m.agent_id
left join core.t_user_meta k on
	k.user_id = a.approved_by
left join core.t_user_meta p on
	p.user_id = a.created_by
left join core.t_user_meta t on
	t.user_id = a.modified_by
left join core.t_country r on
	r.id = m.country_id;
*/
@Service
public class JooqAgentRepository implements QueryResultPicker{

    @Autowired
    public DSLContext context;

    public static final String FIELD_ID = "id";
    public static final String FIELD_FIRST_NAME = "firstName";
    public static final String FIELD_MIDDLE_NAME = "middleName";
    public static final String FIELD_LAST_NAME = "lastName";
    public static final String FIELD_FULL_NAME = "fullName";
    public static final String FIELD_PHONE_NUMBER1 = "phoneNumber1";
    public static final String FIELD_PHONE_NUMBER2 = "phoneNumber2";
    public static final String FIELD_IMAGE_PATH = "imagePath";
    public static final String FIELD_GENDER = "gender";
    public static final String FIELD_BIRTH_DATE = "birthDate";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_IDENTIFICATION = "identification";
    public static final String FIELD_IDENTIFICATION_NUMBER = "identificationNumber";
    public static final String FIELD_IDENTIFICATION_PATH = "identificationPath";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_COUNTRY = "country";
    public static final String FIELD_APPROVED_BY = "approvedBy";
    public static final String FIELD_EXTERNAL_ID = "externalId";
    public static final String FIELD_ACTIVATED_ON = "activatedOn";
    public static final String FIELD_REACTIVATED_ON = "reactivatedOn";
    public static final String FIELD_NON_LOCKED = "nonLocked";
    public static final String FIELD_NON_DISABLED = "nonDisabled";
    public static final String FIELD_NON_LOCKED_PIN = "nonLockedPin";
    public static final String FIELD_PIN_LAST_UPDATED_ON = "pinLastUpdatedOn";
    public static final String FIELD_LAS_PIN_LOCK_REASON = "lasPinLockReason";
    public static final String FIELD_ENROLLED_BY = "enrolledBy";
    public static final String FIELD_CREATED_ON = "createdOn";
    public static final String FIELD_CREATED_BY = "createdBy";
    public static final String FIELD_MODIFIED_BY = "modifiedBy";
    public static final String FIELD_MODIFIED_ON = "modifiedOn";

    private final Field<Long> id = DSL.field("a.id", Long.class);
    private final Field<String> firstName = DSL.field("m.first_name", String.class);
    private final Field<String> middleName = DSL.field("m.middle_name", String.class);
    private final Field<String> lastName = DSL.field("m.last_name", String.class);
    private final Field<String> fullName = DSL.field("CASE WHEN m.middle_name IS NULL THEN concat(m.first_name, ' ', m.last_name) ELSE concat(m.first_name, ' ', m.middle_name, ' ', m.last_name) END", String.class);
    private final Field<String> phoneNumber1 = DSL.field("m.phone_number", String.class);
    private final Field<String> phoneNumber2 = DSL.field("m.phone_number_2", String.class);
    private final Field<String> imagePath = DSL.field("m.image_path", String.class);
    private final Field<String> gender = DSL.field("m.gender", String.class);
    private final Field<Date> birthDate = DSL.field("m.birth_date", Date.class);
    private final Field<String> email = DSL.field("m.email", String.class);
    private final Field<String> identification = DSL.field("m.identification", String.class);
    private final Field<String> identificationNumber = DSL.field("m.identification_number", String.class);
    private final Field<String> identificationPath = DSL.field("m.identification_path", String.class);
    private final Field<String> type = DSL.field("a.type", String.class);
    private final Field<String> country = DSL.field("r.name", String.class);
    private final Field<String> approvedBy = DSL.field("CASE WHEN k.middle_name IS NULL THEN concat(k.first_name, ' ', k.last_name) ELSE concat(k.first_name, ' ', k.middle_name, ' ', k.last_name) END", String.class);
    private final Field<String> externalId = DSL.field("a.external_id", String.class);
    private final Field<Date> activatedOn = DSL.field("a.activated_on", Date.class);
    private final Field<Date> reactivatedOn = DSL.field("a.last_reactivated_on", Date.class);
    private final Field<Boolean> nonLocked = DSL.field("a.non_locked", Boolean.class);
    private final Field<Boolean> nonDisabled = DSL.field("a.non_disabled", Boolean.class);
    private final Field<Boolean> nonLockedPin = DSL.field("a.non_locked_pin", Boolean.class);
    private final Field<Date> pinLastUpdatedOn = DSL.field("a.pin_last_updated_on", Date.class);
    private final Field<String> lasPinLockReason = DSL.field("a.last_pin_lock_reason", String.class);
    private final Field<String> enrolledBy = DSL.field("a.enrolled_by", String.class);
    private final Field<Date> createdOn = DSL.field("a.created_on", Date.class);
    private final Field<String> createdBy = DSL.field("CASE WHEN p.middle_name IS NULL THEN concat(p.first_name, ' ', p.last_name) ELSE concat(p.first_name, ' ', p.middle_name, ' ', p.last_name) END", String.class);
    private final Field<Object> modifiedBy = DSL.field("CASE WHEN t.last_name IS NULL THEN concat(t.first_name, ' ', t.last_name) ELSE concat(t.first_name, ' ', t.middle_name, ' ', t.last_name) END", Object.class);
    private final Field<Date> modifiedOn = DSL.field("a.modified_on", Date.class);

    private final Table<?> coreTable = DSL.table("core.t_agent a\n"
            + "INNER JOIN core.t_user_meta m ON a.id = m.agent_id\n"
            + "LEFT JOIN core.t_user_meta k ON k.user_id = a.approved_by\n"
            + "LEFT JOIN core.t_user_meta p ON p.user_id = a.created_by\n"
            + "LEFT JOIN core.t_user_meta t ON t.user_id = a.modified_by\n"
            + "LEFT JOIN core.t_country r ON r.id = m.country_id\n"
    );

    private JooqFilter jooqFilter;

    public JooqAgentRepository(){
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
                        dbField(id, FIELD_ID),
                        dbField(firstName, FIELD_FIRST_NAME),
                        dbField(middleName, FIELD_MIDDLE_NAME),
                        dbField(lastName, FIELD_LAST_NAME),
                        dbField(fullName, FIELD_FULL_NAME),
                        dbField(phoneNumber1, FIELD_PHONE_NUMBER1),
                        dbField(phoneNumber2, FIELD_PHONE_NUMBER2),
                        dbField(imagePath, FIELD_IMAGE_PATH),
                        dbField(gender, FIELD_GENDER),
                        dbField(birthDate, FIELD_BIRTH_DATE),
                        dbField(email, FIELD_EMAIL),
                        dbField(identification, FIELD_IDENTIFICATION),
                        dbField(identificationNumber, FIELD_IDENTIFICATION_NUMBER),
                        dbField(identificationPath, FIELD_IDENTIFICATION_PATH),
                        dbField(type, FIELD_TYPE),
                        dbField(country, FIELD_COUNTRY),
                        dbField(approvedBy, FIELD_APPROVED_BY),
                        dbField(externalId, FIELD_EXTERNAL_ID),
                        dbField(activatedOn, FIELD_ACTIVATED_ON),
                        dbField(reactivatedOn, FIELD_REACTIVATED_ON),
                        dbField(nonLocked, FIELD_NON_LOCKED),
                        dbField(nonDisabled, FIELD_NON_DISABLED),
                        dbField(nonLockedPin, FIELD_NON_LOCKED_PIN),
                        dbField(pinLastUpdatedOn, FIELD_PIN_LAST_UPDATED_ON),
                        dbField(lasPinLockReason, FIELD_LAS_PIN_LOCK_REASON),
                        dbField(enrolledBy, FIELD_ENROLLED_BY),
                        dbField(createdOn, FIELD_CREATED_ON),
                        dbField(createdBy, FIELD_CREATED_BY),
                        dbField(modifiedBy, FIELD_MODIFIED_BY),
                        dbField(modifiedOn, FIELD_MODIFIED_ON)
                );
    }

    @lombok.Data
    @lombok.NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Result{
        private Long id;
        private String firstName;
        private String middleName;
        private String lastName;
        private String fullName;
        private String phoneNumber1;
        private String phoneNumber2;
        private String imagePath;
        private String gender;
        private Date birthDate;
        private String email;
        private String identification;
        private String identificationNumber;
        private String identificationPath;
        private String type;
        private String country;
        private String approvedBy;
        private String externalId;
        private Date activatedOn;
        private Date reactivatedOn;
        private Boolean nonLocked;
        private Boolean nonDisabled;
        private Boolean nonLockedPin;
        private Date pinLastUpdatedOn;
        private String lasPinLockReason;
        private String enrolledBy;
        private Date createdOn;
        private String createdBy;
        private Object modifiedBy;
        private Date modifiedOn;


    }

    public RecordList<Result> listAndCount(MultiValueMap<String,?> map){
        return new RecordList<>(count(map),list(map));
    }

    public List<Result> list(MultiValueMap<String,?> map){
        return context.fetch(this.query(map)).into(Result.class);
    }

    public Long count(MultiValueMap<String,?> map){
        return context.fetchOne(this.query(addCountParams(map))).getValue(0,Long.class);
    }

}

//endregion
