package com.jajjamind.commons.utils;

import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author akena
 * 19/11/2020
 * 22:42
 **/
public class Validate {

    public static void isTrue(boolean value, String message,Object ... params){
        Assert.isTrue(value,
                String.format(message,params));
    }

    public static void notNull(Object value, String message,String ... params){
        Assert.notNull(value,
                String.format(message,params));
    }

    public static void notNull(Object value, String message){
        Assert.notNull(value,message);
    }

    public static void notEmpty(String value, String message,String ... params){
        Assert.hasLength(value,
                String.format(message,params));
    }

    public static void notEmpty(String value, String message){
        Assert.hasLength(value,
                String.format(message,message));
    }

    public static void isPresent(Optional<?> value, String message, Object ... params){
        if(!value.isPresent()){
             throw new IllegalArgumentException(String.format(message,params));
        }
    }

    public static void isNull(Object value, String message){
        Assert.isNull(value,message);
    }

}
