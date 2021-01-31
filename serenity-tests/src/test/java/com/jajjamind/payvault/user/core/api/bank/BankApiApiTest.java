package com.jajjamind.payvault.user.core.api.bank;

import com.jajjamind.payvault.BaseTestClass;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author akena
 * 13/01/2021
 * 00:57
 **/

@RunWith(SerenityRunner.class)
public class BankApiApiTest extends BaseTestClass {

    @Steps
    BankSteps bankSteps;

    @Before
    public void init(){
        bankSteps.spec = getDefaultRequestSpecification(Boolean.TRUE);
    }

    @Test
    public void shouldAddNewBankAndGetBankById() throws JSONException {
        JSONObject createBankPayload = bankSteps.getBankCreationObject();
        bankSteps.shouldAddNewBank(createBankPayload.toString())
                .thenVerifyThatBankAdded(createBankPayload.getString("accountNumber"));

        //Get the create
        bankSteps.shouldGetBank()
                .thenVerifyThatBankRetrieved();

        //Create another account and retrieve all accounts
        createBankPayload = bankSteps.getBankCreationObject();
        bankSteps.shouldAddNewBank(createBankPayload.toString())
                .thenVerifyThatBankAdded(createBankPayload.getString("accountNumber"))
                .shouldGetAllBanks()
                .thenVerifyThatAllBanksRetrieve();

    }
}
