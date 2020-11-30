package com.jajjamind.payvault.user;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.Assertions;
import org.seleniumhq.jetty9.http.HttpStatus;

import static io.restassured.RestAssured.given;

/**
 * @author akena
 * 19/11/2020
 * 23:41
 **/
public class UserSteps {

    public RequestSpecification spec;
    private Response response;

    @Step
    public  UserSteps clientMakesGetRequestWithoutAuthorizationHeader(){
        response = given(spec).log().all().get("hello");
        response.prettyPrint();
        return this;
    }

    @Step
    public UserSteps requestShouldFailWithUnAuthorizedError(){

        Assertions.assertThat(response)
                .isNotNull();

        Assertions.assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.UNAUTHORIZED_401);

        return this;
    }

    @Step
    public UserSteps requestShouldSucceedWithStatusCode200(){

        Assertions.assertThat(response)
                .isNotNull();

        Assertions.assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK_200);

        return this;
    }


}
