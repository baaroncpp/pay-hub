package com.jajjamind.payvault.user;

import com.jajjamind.payvault.BaseTestClass;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * @author akena
 * 19/11/2020
 * 23:28
 **/
@RunWith(SerenityRunner.class)
public class UserAuthorizationProcessTest extends BaseTestClass {
    @Steps
    UserSteps userSteps;

    RequestSpecification spec;
    @Before
    public void init(){
        spec = getDefaultRequestSpecification(Boolean.TRUE);
        userSteps.spec = spec;
    }

    @Test
    public void shouldFailedToAccessAuthenticatedResources() {
        // GIVEN
        userSteps.spec = getDefaultRequestSpecification();
        // WHEN
        userSteps.clientMakesGetRequestWithoutAuthorizationHeader();
        // THEN
        userSteps.requestShouldFailWithUnAuthorizedError();

    }

    @Test
    public void shouldAccessAuthenticatedResources() {
        // GIVEN
        userSteps.clientMakesGetRequestWithoutAuthorizationHeader();
        // THEN
        userSteps.requestShouldSucceedWithStatusCode200();

    }
}
