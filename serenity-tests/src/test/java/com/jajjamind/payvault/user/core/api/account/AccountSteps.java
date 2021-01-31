package com.jajjamind.payvault.user.core.api.account;

import com.github.javafaker.Faker;
import com.jajjamind.payvault.BaseSteps;
import com.jajjamind.payvault.core.api.account.models.Account;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.List;

/**
 * @author akena
 * 22/01/2021
 * 01:54
 **/
public class AccountSteps extends BaseSteps {

    private static final String PATH = "/accounts";
    private static final String ACCOUNT_GROUP_PATH = "/accounts/group";
    public RequestSpecification spec;
    private Response response;

    public Response getResponse(){
        return response;
    }
    @Step
    public AccountSteps shouldCreateAccountGroup(JSONObject body){
        response = given(spec).body(body.toString()).post(ACCOUNT_GROUP_PATH);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatusCode(), 200);
        return this;
    }

    @Step
    public AccountSteps thenCheckThatAccountGroupCreated(){
        Integer id = response.jsonPath().getInt("id");
        String name = response.jsonPath().getString("name");
        response = given(spec).get(ACCOUNT_GROUP_PATH+"/"+id);
        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.getStatusCode());

        final String nameReturned = response.jsonPath().getString("name");
        Assertions.assertThat(nameReturned)
                .isNotEmpty()
                .isEqualTo(name);
        return this;
    }

    public Integer getGrouping(){
        return response.jsonPath().getInt("id");
    }

    @Step
    public AccountSteps shouldUpdateAccountGroup(JSONObject body) throws JSONException {
        shouldCreateAccountGroup(body);
        String name = response.jsonPath().getString("name");
        Boolean canLiquidate = response.jsonPath().getBoolean("canBulkLiquidate");

        response.prettyPrint();
        JSONObject toUpdate = new JSONObject(response.asString());
        toUpdate.put("name", Faker.instance().app().name());
        toUpdate.put("canBulkLiquidate",Boolean.FALSE);

        response = given(spec).body(toUpdate.toString()).put(ACCOUNT_GROUP_PATH);

        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.getStatusCode());
        Assertions.assertThat(response.jsonPath().getString("name"))
                .isNotEmpty()
                .isNotEqualTo(name);

        Assertions.assertThat(response.jsonPath().getBoolean("canBulkLiquidate"))
                .isFalse();

        return this;
    }

    @Step
    public AccountSteps shouldGetAllAccountGroups() throws JSONException {
        response = given(spec).get(ACCOUNT_GROUP_PATH);
        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.getStatusCode());

        JSONArray array = new JSONArray(response.asString());
        Assert.assertTrue(array.length() > 1);

        return this;
    }



    @Step
    public AccountSteps shouldCreateAccount(JSONObject object){

        response = given(spec).log().all().body(object.toString()).post(PATH);
        checkThatCreationResponseOk(response);
        return this;
    }

    @Step
    public AccountSteps thenCheckThatAccountCreated(){
        final Integer id = response.jsonPath().getInt("id");
        final String code = response.jsonPath().getString("code");
        response = given(spec).get(PATH+"/"+id);

        checkThatCreationResponseOk(response);
        Assertions.assertThat(response.jsonPath().getString("code"))
                .isNotEmpty()
                .isEqualToIgnoringCase(code);

        Assertions.assertThat(response.jsonPath().getString("createdBy"))
                .isNotEmpty();
        return this;
    }

    @Step
    public AccountSteps shouldGetAccountsByGroup(Integer groupIdentifier){
        final String path = PATH+"/grouping/"+groupIdentifier;
        response = given(spec).get(path);
        Assertions.assertThat(response)
                .isNotNull();
        Assertions.assertThat(response.getStatusCode())
                .isEqualTo(200);

        List<Account> accountList = (List<Account>)response.as(List.class);
        Assertions.assertThat(accountList.size())
                .isEqualTo(2);

        return this;
    }



}
