package com.jajjamind.payvault.core.repository.account;

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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.jajjamind.payvault.core.repository.DbField.dbField;

/**
 * @author akena
 * 25/01/2021
 * 17:40
 **/
//region Begin Code
/*
This file has been generated by Jooqify.groovy [Sun Jan 31 15:55:43 EAT 2021]
*/


/*
SELECT
	t.id as id_long,
    t.name as name_string,
    t.code as code_string,
    t.type as accountType_string,
    t.balance_to_notify_at as balanceToNotifyAt_decimal,
    t.balance_notification_sent_on as balanceNotificationSentOn_date,
    t.available_balance as availableBalance_decimal,
    t.status_description as statusDescription_string,
    t.status as accountStatus_string,
    t.activated_on as activatedOn_date,
    case
		when a.middle_name is null then concat(a.first_name, ' ', a.last_name)
		else concat(a.first_name, ' ', a.middle_name, ' ', a.last_name)
	end as activatedBy_string,
    t.suspended_on as suspendedOn_date,
    case
        when s.middle_name is null then concat(s.first_name, ' ', s.last_name)
        else concat(s.first_name, ' ', s.middle_name, ' ', s.last_name)
	end as suspendedBy_string,
    t.closed_on as closedOn_date,
    case
        when c.middle_name is null then concat(c.first_name, ' ', c.last_name)
        else concat(c.first_name, ' ', c.middle_name, ' ', c.last_name)
	end as closedBy_string,
    t.created_on as createdOn_date,
    t.modified_on as modifiedOn_date,
    case
        when k.middle_name is null then concat(k.first_name, ' ', k.last_name)
        else concat(k.first_name, ' ', k.middle_name, ' ', k.last_name)
	end as createdBy_string,
    case
        when m.middle_name is null then concat(m.first_name, ' ', m.last_name)
        else concat(m.first_name, ' ', m.middle_name, ' ',m.last_name)
	end as modifiedBy_string,
    is_assigned as isAssigned_boolean
FROM core.t_account t
    left join core.t_user_meta k on t.created_by = k.user_id
    left join core.t_user_meta m on m.user_id  = t.modified_by
    left join core.t_user_meta c on c.user_id = t.closed_by
    left join core.t_user_meta s on c.user_id = t.suspended_by
    LEFT JOIN core.t_user_meta a ON c.user_id = t.activated_by;
*/
@Service
public class JooqAccountRepository implements QueryResultPicker{

    @Autowired
    public DSLContext context;

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_CODE = "code";
    public static final String FIELD_ACCOUNT_TYPE = "accountType";
    public static final String FIELD_ACCOUNT_GROUPING = "accountGrouping";
    public static final String FIELD_BALANCE_TO_NOTIFY_AT = "balanceToNotifyAt";
    public static final String FIELD_BALANCE_NOTIFICATION_SENT_ON = "balanceNotificationSentOn";
    public static final String FIELD_AVAILABLE_BALANCE = "availableBalance";
    public static final String FIELD_STATUS_DESCRIPTION = "statusDescription";
    public static final String FIELD_ACCOUNT_STATUS = "accountStatus";
    public static final String FIELD_ACTIVATED_ON = "activatedOn";
    public static final String FIELD_ACTIVATED_BY = "activatedBy";
    public static final String FIELD_SUSPENDED_ON = "suspendedOn";
    public static final String FIELD_SUSPENDED_BY = "suspendedBy";
    public static final String FIELD_CLOSED_ON = "closedOn";
    public static final String FIELD_CLOSED_BY = "closedBy";
    public static final String FIELD_CREATED_ON = "createdOn";
    public static final String FIELD_MODIFIED_ON = "modifiedOn";
    public static final String FIELD_CREATED_BY = "createdBy";
    public static final String FIELD_MODIFIED_BY = "modifiedBy";
    public static final String FIELD_IS_ASSIGNED = "isAssigned";

    private final Field<Long> id = DSL.field("t.id", Long.class);
    private final Field<String> name = DSL.field("t.name", String.class);
    private final Field<String> code = DSL.field("t.code", String.class);
    private final Field<String> accountType = DSL.field("t.type", String.class);
    private final Field<String> accountGrouping = DSL.field("t.grouping", String.class);
    private final Field<BigDecimal> balanceToNotifyAt = DSL.field("t.balance_to_notify_at", BigDecimal.class);
    private final Field<Date> balanceNotificationSentOn = DSL.field("t.balance_notification_sent_on", Date.class);
    private final Field<BigDecimal> availableBalance = DSL.field("t.available_balance", BigDecimal.class);
    private final Field<String> statusDescription = DSL.field("t.status_description", String.class);
    private final Field<String> accountStatus = DSL.field("t.status", String.class);
    private final Field<Date> activatedOn = DSL.field("t.activated_on", Date.class);
    private final Field<String> activatedBy = DSL.field("CASE WHEN a.middle_name IS NULL THEN concat(a.first_name, ' ', a.last_name) ELSE concat(a.first_name, ' ', a.middle_name, ' ', a.last_name) END", String.class);
    private final Field<Date> suspendedOn = DSL.field("t.suspended_on", Date.class);
    private final Field<String> suspendedBy = DSL.field("CASE WHEN s.middle_name IS NULL THEN concat(s.first_name, ' ', s.last_name) ELSE concat(s.first_name, ' ', s.middle_name, ' ', s.last_name) END", String.class);
    private final Field<Date> closedOn = DSL.field("t.closed_on", Date.class);
    private final Field<String> closedBy = DSL.field("CASE WHEN c.middle_name IS NULL THEN concat(c.first_name, ' ', c.last_name) ELSE concat(c.first_name, ' ', c.middle_name, ' ', c.last_name) END", String.class);
    private final Field<Date> createdOn = DSL.field("t.created_on", Date.class);
    private final Field<Date> modifiedOn = DSL.field("t.modified_on", Date.class);
    private final Field<String> createdBy = DSL.field("CASE WHEN k.middle_name IS NULL THEN concat(k.first_name, ' ', k.last_name) ELSE concat(k.first_name, ' ', k.middle_name, ' ', k.last_name) END", String.class);
    private final Field<String> modifiedBy = DSL.field("CASE WHEN m.middle_name IS NULL THEN concat(m.first_name, ' ', m.last_name) ELSE concat(m.first_name, ' ', m.middle_name, ' ', m.last_name) END", String.class);
    private final Field<Boolean> isAssigned = DSL.field("is_assigned", Boolean.class);

    private final Table<?> coreTable = DSL.table("core.t_account t\n"
            + "LEFT JOIN core.t_user_meta k ON t.created_by = k.user_id\n"
            + "LEFT JOIN core.t_user_meta m ON m.user_id = t.modified_by\n"
            + "LEFT JOIN core.t_user_meta c ON c.user_id = t.closed_by\n"
            + "LEFT JOIN core.t_user_meta s ON c.user_id = t.suspended_by\n"
            + "LEFT JOIN core.t_user_meta a ON c.user_id = t.activated_by\n"
    );

    private JooqFilter jooqFilter;

    public JooqAccountRepository(){
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

    public String getAllFields(){
        final StringBuilder builder = new StringBuilder();
        builder.append(FIELD_ID);
        builder.append(",");
        builder.append(FIELD_NAME);
        builder.append(",");
        builder.append(FIELD_CODE);
        builder.append(",");
        builder.append(FIELD_ACCOUNT_TYPE);
        builder.append(",");
        builder.append(FIELD_BALANCE_TO_NOTIFY_AT);
        builder.append(",");
        builder.append(FIELD_BALANCE_NOTIFICATION_SENT_ON);
        builder.append(",");
        builder.append(FIELD_AVAILABLE_BALANCE);
        builder.append(",");
        builder.append(FIELD_STATUS_DESCRIPTION);
        builder.append(",");
        builder.append(FIELD_ACCOUNT_STATUS);
        builder.append(",");
        builder.append(FIELD_ACTIVATED_ON);
        builder.append(",");
        builder.append(FIELD_ACTIVATED_BY);
        builder.append(",");
        builder.append(FIELD_SUSPENDED_ON);
        builder.append(",");
        builder.append(FIELD_SUSPENDED_BY);
        builder.append(",");
        builder.append(FIELD_CLOSED_ON);
        builder.append(",");
        builder.append(FIELD_CLOSED_BY);
        builder.append(",");
        builder.append(FIELD_CREATED_ON);
        builder.append(",");
        builder.append(FIELD_MODIFIED_ON);
        builder.append(",");
        builder.append(FIELD_CREATED_BY);
        builder.append(",");
        builder.append(FIELD_MODIFIED_BY);
        builder.append(",");
        builder.append(FIELD_ACCOUNT_GROUPING)
                .append(",");
        builder.append(FIELD_IS_ASSIGNED);
        return builder.toString();
    }

    private void initFilterer(){
        jooqFilter = new JooqFilter()
                .fields(
                        dbField(id, FIELD_ID),
                        dbField(name, FIELD_NAME),
                        dbField(code, FIELD_CODE),
                        dbField(accountType, FIELD_ACCOUNT_TYPE),
                        dbField(accountGrouping, FIELD_ACCOUNT_GROUPING),
                        dbField(balanceToNotifyAt, FIELD_BALANCE_TO_NOTIFY_AT),
                        dbField(balanceNotificationSentOn, FIELD_BALANCE_NOTIFICATION_SENT_ON),
                        dbField(availableBalance, FIELD_AVAILABLE_BALANCE),
                        dbField(statusDescription, FIELD_STATUS_DESCRIPTION),
                        dbField(accountStatus, FIELD_ACCOUNT_STATUS),
                        dbField(activatedOn, FIELD_ACTIVATED_ON),
                        dbField(activatedBy, FIELD_ACTIVATED_BY),
                        dbField(suspendedOn, FIELD_SUSPENDED_ON),
                        dbField(suspendedBy, FIELD_SUSPENDED_BY),
                        dbField(closedOn, FIELD_CLOSED_ON),
                        dbField(closedBy, FIELD_CLOSED_BY),
                        dbField(createdOn, FIELD_CREATED_ON),
                        dbField(modifiedOn, FIELD_MODIFIED_ON),
                        dbField(createdBy, FIELD_CREATED_BY),
                        dbField(modifiedBy, FIELD_MODIFIED_BY),
                        dbField(isAssigned, FIELD_IS_ASSIGNED)
                );
    }


    @lombok.Data
    @lombok.NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Result{
        private Long id;
        private String name;
        private String code;
        private String accountType;
        private String accountGrouping;
        private BigDecimal balanceToNotifyAt;
        private Date balanceNotificationSentOn;
        private BigDecimal availableBalance;
        private String statusDescription;
        private String accountStatus;
        private Date activatedOn;
        private String activatedBy;
        private Date suspendedOn;
        private String suspendedBy;
        private Date closedOn;
        private String closedBy;
        private Date createdOn;
        private Date modifiedOn;
        private String createdBy;
        private String modifiedBy;
        private Boolean isAssigned;


    }

    public RecordList<Result> listAndCount(MultiValueMap<String,?> map){
        return new RecordList<>(count(map),list(map));
    }

    public List<Result> list(MultiValueMap<String,?> map){
        final Select<Record> mQuery = this.query(map);
        return context.fetch(mQuery).into(Result.class);
    }

    public Long count(MultiValueMap<String,?> map){
        return context.fetchOne(this.query(addCountParams(map))).getValue(0,Long.class);
    }

}

//endregion