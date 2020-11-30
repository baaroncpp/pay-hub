package com.jajjamind.payvault;

import com.jajjamind.commons.utils.MapUtils;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;

import java.util.Map;

/**
 * @author akena
 * 23/11/2020
 * 13:20
 **/
public abstract class BaseTestClass {

    //Load from test properties file
    private static final String  ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";
    private static final String NORMAL_USERNAME = "user";
    private static final String NORMAL_PASSWORD = "user";
    private static final String USERNAME_HEADER = "username";
    private static final String PASSWORD_HEADER = "password";
    private static final String GRANT_TYPE = "password";
    private static final Integer PORT = 9005;
    private static final String BASE_PATH = "http://localhost";

    public static RequestSpecification getDefaultRequestSpecification(){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_PATH)
                .setPort(PORT)
                .build();
    }

    public static RequestSpecification getDefaultRequestSpecification(boolean isAdmin){ ;
        RequestSpecification spec = getDefaultRequestSpecification();

        Map queryParams = MapUtils
                .create(USERNAME_HEADER,isAdmin ? ADMIN_USERNAME : NORMAL_USERNAME )
                .put(PASSWORD_HEADER,isAdmin ? ADMIN_PASSWORD : NORMAL_PASSWORD)
                .put("grant_type",GRANT_TYPE)
                .put("scope","read")
                .put("client_id","client")
                .put("client_secret","secret")
                .build();

        Response response = RestAssured.given(spec).log().all().queryParams(queryParams).post("oauth/token");
        response.prettyPrint();

        Assertions.assertThat(response.getStatusCode())
                .isEqualTo(200);

        Assertions.assertThat(response.body().asString())
                .isNotEmpty();

        final String bearerToken = response.jsonPath().getString("access_token");

        //Remove username and password headers and create new spec
        spec.header("Authorization", "Bearer "+bearerToken);

        return spec;

    }


}
