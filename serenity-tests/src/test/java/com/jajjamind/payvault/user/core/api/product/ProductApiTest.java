package com.jajjamind.payvault.user.core.api.product;

import com.github.javafaker.Faker;
import com.jajjamind.payvault.BaseTestClass;
import com.jajjamind.payvault.core.jpa.models.enums.PricingTypeEnum;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

/**
 * @author akena
 * 21/01/2021
 * 12:39
 **/
@RunWith(SerenityRunner.class)
public class ProductApiTest extends BaseTestClass {

    @Steps
    ProductSteps productSteps;

    @Before
    public void init(){
        productSteps.spec = getDefaultRequestSpecification(Boolean.TRUE);
    }

    @Test
    public void testThatCanCreateAndGetProductTemplate() throws JSONException {
        JSONObject templateObject = ProductPayload.createProductCommissionTemplateWithChargePayload(BigDecimal.valueOf(2300),0, PricingTypeEnum.FLAG_CHARGE.name());
        productSteps.createProductCommissionTemplate(templateObject.toString())
                .thenCheckThatProductTemplateCreated()
                .thenGetProductCommissionTemplateById();

        var name = Faker.instance().ancient().god();

        productSteps.thenUpdateProductCommissionTemplate(name)
                .thenCheckThatProductUpdateSuccessful(name);


    }

}
