package com.jajjamind.payvault.user.core.api.product;

import com.github.javafaker.Faker;
import com.jajjamind.payvault.core.api.account.models.Account;
import com.jajjamind.payvault.core.jpa.models.enums.PricingTypeEnum;
import com.jajjamind.payvault.core.jpa.models.enums.ProductCategoryEnum;
import com.jajjamind.payvault.core.jpa.models.enums.StatusEnum;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author akena
 * 21/01/2021
 * 12:39
 **/
public class ProductPayload {

    private static Faker faker = new Faker();
    public static JSONObject createProductPayload(String productCategory,boolean hasCharge,boolean hasTariff) throws JSONException {

        JSONObject productAccount = new JSONObject();
        productAccount.put("id",2);

        JSONObject object = new JSONObject();
        object.put("productAccount",productAccount);
        object.put("name",faker.gameOfThrones().character());
        object.put("productCategory",productCategory);
        // object.put("productCode","");
        object.put("officialName", faker.artist().name());
        object.put("hasCharge",hasCharge);
        object.put("hasTariff",hasTariff);

        return  object;
    }

    public static JSONObject createProductCommissionTemplateWithChargePayload(BigDecimal chargeAmount,float chargePercent,String pricingType) throws JSONException {

        JSONObject object = new JSONObject();

        object.put("name",faker.gameOfThrones().character());
        object.put("amount",chargeAmount);
        object.put("percent",chargePercent);
        object.put("pricingType",pricingType);
        object.put("currency","UGX");
        object.put("status","ACTIVE");

        return object;
    }

    public static JSONObject createProductCommissionTemplateWithTariffPayload(BigDecimal fromAmount,BigDecimal toAmount,String tariffGroupIdentifier) throws JSONException {

        JSONObject object = new JSONObject();

        object.put("name",faker.gameOfThrones().character());
        object.put("pricingType","TARIFF");
        object.put("fromAmount",fromAmount);
        object.put("toAmount",toAmount);
        object.put("tariffGroupIdentifier",tariffGroupIdentifier);
        object.put("currency","UGX");
        object.put("status","ACTIVE");

        return object;
    }

}
