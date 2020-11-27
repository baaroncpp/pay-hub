package com.jajjamind.payvault;

import io.restassured.authentication.AuthenticationScheme;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.internal.log.LogRepository;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;

import java.util.List;

/**
 * @author akena
 * 26/11/2020
 * 01:22
 **/
public class DefaultRequestSpecBuilder extends RequestSpecificationImpl {

    public void DefaultRequestSpecBuilder(){

    }

    public DefaultRequestSpecBuilder(String baseURI, int requestPort, String basePath, AuthenticationScheme defaultAuthScheme, List<Filter> filters, RequestSpecification defaultSpec, boolean urlEncode, RestAssuredConfig restAssuredConfig, LogRepository logRepository, ProxySpecification proxySpecification) {
        super(baseURI, requestPort, basePath, defaultAuthScheme, filters, defaultSpec, urlEncode, restAssuredConfig, logRepository, proxySpecification);
    }
}
