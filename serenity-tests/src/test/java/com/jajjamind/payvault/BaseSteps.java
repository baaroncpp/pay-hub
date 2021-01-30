package com.jajjamind.payvault;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.springframework.http.HttpStatus;

/**
 * @author akena
 * 13/01/2021
 * 16:09
 **/
public abstract class BaseSteps {

    public RequestSpecification given(RequestSpecification spec){
        return RestAssured.given(spec)
                .header("Content-Type","application/json");
    }

    public void checkThatCreationResponseOk(Response response){
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCode());
    }

}
