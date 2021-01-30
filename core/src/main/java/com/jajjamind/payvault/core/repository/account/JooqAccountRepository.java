package com.jajjamind.payvault.core.repository.account;

import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.account.models.Account;
import com.jajjamind.payvault.core.repository.QueryResultPicker;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author akena
 * 25/01/2021
 * 17:40
 **/

@Service
public class JooqAccountRepository implements QueryResultPicker {

    @Autowired
    DSLContext context;


    public Account findAccountNotNull(Long id) throws Exception {

        final String query =
                "SELECT t.id, t.name," +
                        " t.code, t.type as accountType, " +
                        " t.grouping as accountGrouping," +
                        " t.balance_to_notify_at as balanceToNotifyAt," +
                        " t.balance_notification_sent_on as balanceNotificationSentOn," +
                        " t.available_balance as availableBalance," +
                        " t.status_description as statusDescription," +
                        " t.status as accountStatus, "+
                        " t.activated_on as activatedOn," +
                        " concat( a.first_name ,' ',a.last_name ) as activatedBy," +
                        " t.suspended_on as suspendedOn," +
                        " concat( s.first_name ,' ',s.last_name ) as suspendedBy," +
                        " t.closed_on as closedOn," +
                        " concat( c.first_name ,' ',c.last_name ) as closedBy ," +
                        " t.created_on as createdOn," +
                        " t.modified_on as modifiedOn," +
                        " concat( k.first_name,' ',k.last_name ) as createdBy ," +
                        " concat( m.first_name ,' ',m.last_name ) as modifiedBy," +
                        " is_assigned as isAssigned" +
                " FROM core.t_account t " +
                    " left join core.t_user_meta k on t.created_by = k.user_id " +
                    " left join core.t_user_meta m on m.user_id  = t.modified_by " +
                    " left join t_user_meta c on c.user_id = t.closed_by " +
                    " left join t_user_meta s on c.user_id = t.suspended_by " +
                    " left join t_user_meta a on a.user_id = t.activated_by " +
                        " WHERE t.id = ?";

        Optional<Record> result = context.fetch(query,id).stream().findAny();

        Validate.isTrue(result.isPresent(),"No account found with ID %s",id);

        return getResultSingle(result.get(),Account.class);

    }
}

//endregion
