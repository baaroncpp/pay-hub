package com.jajjamind.payvault.user.core.api.product;

import com.jajjamind.payvault.BaseSteps;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

/**
 * @author akena
 * 21/01/2021
 * 12:39
 **/
public class ProductSteps extends BaseSteps {

    private static final  String PATH = "/product";
    private static final String PRODUCT_COMMISSION_TEMPLATE_PATH = PATH+"/commission/template";
    public RequestSpecification spec;
    private Response response;
    public Integer id;

    @Step
    public ProductSteps createProductCommissionTemplate(String productCommissionTemplate){

        response = given(spec).body(productCommissionTemplate).post(PRODUCT_COMMISSION_TEMPLATE_PATH);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        id = response.jsonPath().getInt("id");
        return this;
    }

    @Step
    public ProductSteps thenCheckThatProductTemplateCreated(){
        Assertions.assertThat(response.jsonPath().getString("name"))
                .isNotNull()
                .isNotEmpty();
        return this;
    }

    @Step
    public ProductSteps thenGetProductCommissionTemplateById()
    {
        final String productCommissionTemplatePath = PRODUCT_COMMISSION_TEMPLATE_PATH+"/"+id;
        response = given(spec).log().all().get(productCommissionTemplatePath);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.jsonPath().getInt("id"))
                .isEqualTo(id);
        Assertions.assertThat(response.jsonPath().getString("name"))
                .isNotNull();

        return this;
    }

    @Step
    public ProductSteps thenUpdateProductCommissionTemplate(String name) throws JSONException {

        JSONObject object = new JSONObject(response.asString());
        object.put("name",name);

        response = given(spec).body(object.toString()).put(PRODUCT_COMMISSION_TEMPLATE_PATH);

        Assertions.assertThat(response).isNotNull();
        var ok = HttpStatus.OK;
        Assertions.assertThat(response.getStatusCode()).isEqualTo(ok.value());

        return this;
    }


    @Step
    public ProductSteps thenCheckThatProductUpdateSuccessful(String newName){
        thenGetProductCommissionTemplateById();
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(newName);
        return this;
    }
}
