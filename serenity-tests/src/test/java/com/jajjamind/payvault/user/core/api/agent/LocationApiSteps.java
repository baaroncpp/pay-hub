package com.jajjamind.payvault.user.core.api.agent;

import com.jajjamind.payvault.BaseSteps;
import com.jajjamind.payvault.core.api.agent.models.District;
import com.jajjamind.payvault.core.api.bank.models.Bank;
import com.jajjamind.payvault.user.core.api.bank.BankSteps;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * @author akena
 * 13/01/2021
 * 20:54
 **/
public class LocationApiSteps extends BaseSteps {

    private static String PATH = "/location";
    public RequestSpecification spec;
    private Response response;


    public LocationApiSteps shouldGetAllDistricts(){
        response = given(spec).log().all().get(PATH+"/district/all");
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        return this;
    }

    public LocationApiSteps thenVerifyThatAllDistrictsRetrieve() throws JSONException {
        response.prettyPrint();
        JSONArray array = new JSONArray(response.asString());
        Assertions.assertThat(array.length()).isEqualTo(112);
        return this;
    }
}
