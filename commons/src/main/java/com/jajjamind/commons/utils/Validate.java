package com.jajjamind.commons.utils;

import com.jajjamind.commons.exceptions.BadRequestException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author akena
 * 19/11/2020
 * 22:42
 **/
public class Validate {

    public static void isTrue(boolean value, String message,Object ... params){
        if (!value) {
            throw new BadRequestException(message,params);
        }
    }

    public static void notNull(Object value, String message,String ... params){

        if (value == null) {
            throw new BadRequestException(message,params);
        }
    }

    public static void notEmpty(String value, String message,String ... params){
        if (!StringUtils.hasLength(value)) {
            throw new BadRequestException(message,params);
        }
    }

    public static void notEmpty(String value, String message){
        if (!StringUtils.hasLength(value)) {
            throw new BadRequestException(message);
        }
    }

    public static void isPresent(Optional<?> value, String message, Object ... params){
        if(!value.isPresent()){
             throw new BadRequestException(String.format(message,params));
        }
    }
}
