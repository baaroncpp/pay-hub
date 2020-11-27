package com.jajjamind.commons.utils;

import org.springframework.util.Assert;

/**
 * @author akena
 * 19/11/2020
 * 22:42
 **/
public class Validate {

    public static void isTrue(boolean value, String message,String ... params){
        Assert.isTrue(value,
                String.format(message,params));
    }
}
