package com.jajjamind.payvault.user.core.api.account;

import com.github.javafaker.Faker;
import com.jajjamind.payvault.BaseTestClass;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Random;


/**
 * @author akena
 * 22/01/2021
 * 01:54
 **/
public class AccountPayload {

    public static JSONObject getCreateAccountPayload(String accountType,Integer accountGrouping) throws JSONException {

        JSONObject object = new JSONObject();

        if(accountGrouping != null){
            JSONObject object1 = new JSONObject();
            object1.put("id",accountGrouping);
            object1.put("canBulkLiquidate",Boolean.FALSE);
            object.put("accountGrouping",object1);

        }
        object.put("name",BaseTestClass.getFaker().app().name());
        object.put("accountType",accountType);
        object.put("balanceToNotifyAt",500);

        return object;
    }

    public static JSONObject getCreateAccountGrouping(boolean canBulkLiquidate) throws JSONException {
        Faker faker = BaseTestClass.getFaker();
        JSONObject object = new JSONObject();
        object.put("name", faker.app().name());
        object.put("note",faker.lorem().paragraph());
        object.put("canBulkLiquidate",canBulkLiquidate);

        return object;

    }
}
