package com.jajjamind.payvault.core.repository.agent;


//region Begin Code
/*
This file has been generated by Jooqify.groovy [Tue Feb 23 21:48:09 EAT 2021]
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
tc.id as id_long,
tc.business_name  as businessName_string,
tc.nature_of_business  as natureOfBusiness_string,
tc.physical_address as physicalAddress_string,
tc.tin_number as tinNumber_string,
tc.contact_person as contactPerson_string,
tc.contact_identification as contactIdType_string,
tc.contact_identification_number as contactIdNumber_string,
tc.contact_identification_path as contactIdPath_string,
tc.contact_phone_number as contactPhoneNumber_string,
tc.email as email_string,
tc.pv_registration_serial as payHubRegistrationSerial_string,
tc.note as note_string,
d.name as district_string,
d.region as region_string,
c.name as countryOfRegistration_string,
case
        when k.middle_name is null then concat(k.first_name, ' ', k.last_name)
        else concat(k.first_name, ' ', k.middle_name, ' ', k.last_name)
end as createdBy_string,
case
    when m.middle_name is null then concat(m.first_name, ' ', m.last_name)
    else concat(m.first_name, ' ', m.middle_name, ' ',m.last_name)
end as modifiedBy_string,
tc.created_on as createdOn_date,
tc.modified_on as modifiedOn_date
from  t_company tc
left join t_district d on d.id = tc.district
left join t_user_meta k on k.user_id = tc.created_by
left join t_user_meta m on m.user_id = tc.modified_by
left join t_country c on c.id = tc.registration_country ;
*/
@Service
public class JooqCompanyRepository implements QueryResultPicker{

    @Autowired
    public DSLContext context;

    public static final String FIELD_ID = "id";
    public static final String FIELD_BUSINESS_NAME = "businessName";
    public static final String FIELD_NATURE_OF_BUSINESS = "natureOfBusiness";
    public static final String FIELD_PHYSICAL_ADDRESS = "physicalAddress";
    public static final String FIELD_TIN_NUMBER = "tinNumber";
    public static final String FIELD_CONTACT_PERSON = "contactPerson";
    public static final String FIELD_CONTACT_ID_TYPE = "contactIdType";
    public static final String FIELD_CONTACT_ID_NUMBER = "contactIdNumber";
    public static final String FIELD_CONTACT_ID_PATH = "contactIdPath";
    public static final String FIELD_CONTACT_PHONE_NUMBER = "contactPhoneNumber";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PAY_HUB_REGISTRATION_SERIAL = "payHubRegistrationSerial";
    public static final String FIELD_NOTE = "note";
    public static final String FIELD_DISTRICT = "district";
    public static final String FIELD_REGION = "region";
    public static final String FIELD_COUNTRY_OF_REGISTRATION = "countryOfRegistration";
    public static final String FIELD_CREATED_BY = "createdBy";
    public static final String FIELD_MODIFIED_BY = "modifiedBy";
    public static final String FIELD_CREATED_ON = "createdOn";
    public static final String FIELD_MODIFIED_ON = "modifiedOn";

    private final Field<Long> id = DSL.field("tc.id", Long.class);
    private final Field<String> businessName = DSL.field("tc.business_name", String.class);
    private final Field<String> natureOfBusiness = DSL.field("tc.nature_of_business", String.class);
    private final Field<String> physicalAddress = DSL.field("tc.physical_address", String.class);
    private final Field<String> tinNumber = DSL.field("tc.tin_number", String.class);
    private final Field<String> contactPerson = DSL.field("tc.contact_person", String.class);
    private final Field<String> contactIdType = DSL.field("tc.contact_identification", String.class);
    private final Field<String> contactIdNumber = DSL.field("tc.contact_identification_number", String.class);
    private final Field<String> contactIdPath = DSL.field("tc.contact_identification_path", String.class);
    private final Field<String> contactPhoneNumber = DSL.field("tc.contact_phone_number", String.class);
    private final Field<String> email = DSL.field("tc.email", String.class);
    private final Field<String> payHubRegistrationSerial = DSL.field("tc.pv_registration_serial", String.class);
    private final Field<String> note = DSL.field("tc.note", String.class);
    private final Field<String> district = DSL.field("d.name", String.class);
    private final Field<String> region = DSL.field("d.region", String.class);
    private final Field<String> countryOfRegistration = DSL.field("c.name", String.class);
    private final Field<String> createdBy = DSL.field("CASE WHEN k.middle_name IS NULL THEN concat(k.first_name, ' ', k.last_name) ELSE concat(k.first_name, ' ', k.middle_name, ' ', k.last_name) END", String.class);
    private final Field<String> modifiedBy = DSL.field("CASE WHEN m.middle_name IS NULL THEN concat(m.first_name, ' ', m.last_name) ELSE concat(m.first_name, ' ', m.middle_name, ' ', m.last_name) END", String.class);
    private final Field<Date> createdOn = DSL.field("tc.created_on", Date.class);
    private final Field<Date> modifiedOn = DSL.field("tc.modified_on", Date.class);

    private final Table<?> coreTable = DSL.table("t_company tc\n"
            + "LEFT JOIN t_district d ON d.id = tc.district\n"
            + "LEFT JOIN t_user_meta k ON k.user_id = tc.created_by\n"
            + "LEFT JOIN t_user_meta m ON m.user_id = tc.modified_by\n"
            + "LEFT JOIN t_country c ON c.id = tc.registration_country\n"
    );

    private JooqFilter jooqFilter;

    public JooqCompanyRepository(){
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
                        dbField(businessName, FIELD_BUSINESS_NAME),
                        dbField(natureOfBusiness, FIELD_NATURE_OF_BUSINESS),
                        dbField(physicalAddress, FIELD_PHYSICAL_ADDRESS),
                        dbField(tinNumber, FIELD_TIN_NUMBER),
                        dbField(contactPerson, FIELD_CONTACT_PERSON),
                        dbField(contactIdType, FIELD_CONTACT_ID_TYPE),
                        dbField(contactIdNumber, FIELD_CONTACT_ID_NUMBER),
                        dbField(contactIdPath, FIELD_CONTACT_ID_PATH),
                        dbField(contactPhoneNumber, FIELD_CONTACT_PHONE_NUMBER),
                        dbField(email, FIELD_EMAIL),
                        dbField(payHubRegistrationSerial, FIELD_PAY_HUB_REGISTRATION_SERIAL),
                        dbField(note, FIELD_NOTE),
                        dbField(district, FIELD_DISTRICT),
                        dbField(region, FIELD_REGION),
                        dbField(countryOfRegistration, FIELD_COUNTRY_OF_REGISTRATION),
                        dbField(createdBy, FIELD_CREATED_BY),
                        dbField(modifiedBy, FIELD_MODIFIED_BY),
                        dbField(createdOn, FIELD_CREATED_ON),
                        dbField(modifiedOn, FIELD_MODIFIED_ON)
                );
    }

    @lombok.Data
    @lombok.NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Result{
        private Long id;
        private String businessName;
        private String natureOfBusiness;
        private String physicalAddress;
        private String tinNumber;
        private String contactPerson;
        private String contactIdType;
        private String contactIdNumber;
        private String contactIdPath;
        private String contactPhoneNumber;
        private String email;
        private String payHubRegistrationSerial;
        private String note;
        private String district;
        private String region;
        private String countryOfRegistration;
        private String createdBy;
        private String modifiedBy;
        private Date createdOn;
        private Date modifiedOn;


    }

    public RecordList<Result> listAndCount(MultiValueMap<String,?> map){
        final RecordList<Result> result  = new RecordList<Result>(count(map),list(map));
        result.setOffset(map.containsKey("offset") ? Integer.valueOf((String)map.getFirst("offset")) : 0);
        result.setRecordsFiltered(result.getRecords().size());
        return result;
    }

    public List<Result> list(MultiValueMap<String,?> map){
        return context.fetch(this.query(map)).into(Result.class);
    }

    public Long count(MultiValueMap<String,?> map){
        Record result  = context.fetchOne(this.query(addCountParams(map)));
        if(result != null){
            return result.get(0, Long.class);
        }

        return 0L;
    }

    public String getAllFields(){
        final StringBuilder builder = new StringBuilder();
        builder.append(FIELD_ID);
        builder.append(",");
        builder.append(FIELD_BUSINESS_NAME);
        builder.append(",");
        builder.append(FIELD_NATURE_OF_BUSINESS);
        builder.append(",");
        builder.append(FIELD_PHYSICAL_ADDRESS);
        builder.append(",");
        builder.append(FIELD_TIN_NUMBER);
        builder.append(",");
        builder.append(FIELD_CONTACT_PERSON);
        builder.append(",");
        builder.append(FIELD_CONTACT_ID_TYPE);
        builder.append(",");
        builder.append(FIELD_CONTACT_ID_NUMBER);
        builder.append(",");
        builder.append(FIELD_CONTACT_ID_PATH);
        builder.append(",");
        builder.append(FIELD_CONTACT_PHONE_NUMBER);
        builder.append(",");
        builder.append(FIELD_EMAIL);
        builder.append(",");
        builder.append(FIELD_PAY_HUB_REGISTRATION_SERIAL);
        builder.append(",");
        builder.append(FIELD_NOTE);
        builder.append(",");
        builder.append(FIELD_DISTRICT);
        builder.append(",");
        builder.append(FIELD_REGION);
        builder.append(",");
        builder.append(FIELD_COUNTRY_OF_REGISTRATION);
        builder.append(",");
        builder.append(FIELD_CREATED_BY);
        builder.append(",");
        builder.append(FIELD_MODIFIED_BY);
        builder.append(",");
        builder.append(FIELD_CREATED_ON);
        builder.append(",");
        builder.append(FIELD_MODIFIED_ON);
        return builder.toString();
    }

}

//endregion
