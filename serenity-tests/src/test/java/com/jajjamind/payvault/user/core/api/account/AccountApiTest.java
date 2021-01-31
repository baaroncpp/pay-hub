package com.jajjamind.payvault.user.core.api.account;

import com.jajjamind.payvault.BaseTestClass;
import com.jajjamind.payvault.core.jpa.models.enums.AccountTypeEnum;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author akena
 * 22/01/2021
 * 01:54
 **/
@RunWith(SerenityRunner.class)
public class AccountApiTest extends BaseTestClass {

    @Steps
    AccountSteps accountSteps;

    @Before
    public void init(){
        accountSteps.spec = getDefaultRequestSpecification(Boolean.TRUE);
    }


    @Test
    public void testThatCanCreateAndRetrieveAccountingGroups() throws JSONException {
        JSONObject request = AccountPayload.getCreateAccountGrouping(Boolean.FALSE);
        accountSteps.shouldCreateAccountGroup(request)
                .thenCheckThatAccountGroupCreated();

        JSONObject request2 = AccountPayload.getCreateAccountGrouping(Boolean.TRUE);
        accountSteps
                .shouldUpdateAccountGroup(request2)
                .shouldGetAllAccountGroups();
    }


    @Test
    public void testThatCanCreateAndGetAccountWithoutGroup() throws JSONException {
        JSONObject request = AccountPayload.getCreateAccountPayload(AccountTypeEnum.MAIN.name(),null);
        accountSteps.shouldCreateAccount(request)
                .thenCheckThatAccountCreated();
    }


    @Test
    public void testThatCanCreateAndGetAccountWithGrouping() throws JSONException {
        JSONObject request = AccountPayload.getCreateAccountGrouping(Boolean.FALSE);
        accountSteps.shouldCreateAccountGroup(request)
                .thenCheckThatAccountGroupCreated();

        Integer group = accountSteps.getGrouping();

        JSONObject account1 = AccountPayload.getCreateAccountPayload(AccountTypeEnum.MAIN.name(),group);
        accountSteps.shouldCreateAccount(account1)
                .thenCheckThatAccountCreated();

        JSONObject account2 = AccountPayload.getCreateAccountPayload(AccountTypeEnum.MAIN.name(),group);
        accountSteps.shouldCreateAccount(account2)
                .thenCheckThatAccountCreated();

        accountSteps.shouldGetAccountsByGroup(group);


    }

}
