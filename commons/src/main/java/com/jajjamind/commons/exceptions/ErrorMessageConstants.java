package com.jajjamind.commons.exceptions;

/**
 * @author akena
 * 25/11/2020
 * 12:25
 **/
public class ErrorMessageConstants {

    private ErrorMessageConstants(){

    }

    public static String USER_NOT_FOUND = "User with username %s could not found";
    public static String APP_CLIENT_NOT_FOUND = "App client with identifier %s could not found";
    public static String APP_CLIENT_NOT_ENABLED = "App client with identifier %s has been disabled";
}
