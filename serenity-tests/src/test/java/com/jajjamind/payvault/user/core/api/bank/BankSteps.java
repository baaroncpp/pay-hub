package com.jajjamind.payvault.user.core.api.bank;

import com.github.javafaker.Faker;
import com.jajjamind.payvault.BaseSteps;
import com.jajjamind.payvault.core.api.bank.models.Bank;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author akena
 * 13/01/2021
 * 00:59
 **/
public class BankSteps extends BaseSteps {

    private static String PATH = "/bank";
    public RequestSpecification spec;
    private Response response;
    public Bank currentBank;

    public BankSteps shouldAddNewBank(String payload){
        response = given(spec).log().all().body(payload).post(PATH);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        return this;
    }

    public BankSteps thenVerifyThatBankAdded(String accountNumber){
        currentBank = response.as(Bank.class);
        Assertions.assertThat(currentBank).isNotNull();
        Assertions.assertThat(currentBank.getAccountName()).isNotEmpty();
        Assertions.assertThat(currentBank.getId()).isGreaterThan(0);
        Assertions.assertThat(currentBank.getAccountNumber()).isEqualToIgnoringCase(accountNumber);
        return this;
    }

    public BankSteps shouldGetBank(){
        response = given(spec).log().all().get(PATH+"/"+currentBank.getId());
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        return this;
    }

    public BankSteps shouldGetAllBanks(){
        response = given(spec).log().all().get(PATH+"/all");
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        return this;
    }

    public BankSteps thenVerifyThatBankRetrieved(){
        response.prettyPrint();
        Assertions.assertThat(response.jsonPath().getString("accountNumber")).isEqualToIgnoringCase(currentBank.getAccountNumber());
        Assertions.assertThat(response.jsonPath().getString("accountName")).isEqualToIgnoringCase(currentBank.getAccountName());
        Assertions.assertThat(response.jsonPath().getInt("id")).isEqualTo(currentBank.getId());
        return this;
    }

    public BankSteps thenVerifyThatAllBanksRetrieve() throws JSONException {
        response.prettyPrint();
        JSONArray array = new JSONArray(response.asString());
        Assertions.assertThat(array.length()).isGreaterThan(1);
        return this;
    }

    public JSONObject getCountry() throws JSONException {
        JSONObject object =new JSONObject();
        object.put("isoAlpha2","UG");
        return object;
    }

    public JSONObject getBankCreationObject() throws JSONException {
        Faker faker = new Faker();

        JSONObject object = new JSONObject();
        object.put("bankName",faker.name().lastName());
        object.put("accountName",faker.name().firstName());
        object.put("accountNumber",faker.number().digits(10));
        object.put("branch",faker.gameOfThrones().city());
        object.put("swiftCode",faker.number().digits(3));
        object.put("currency","UGX");
        object.put("country",getCountry());

        return object;
    }
}
