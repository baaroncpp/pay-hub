package com.jajjamind.commons.utils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author akena
 * 29/11/2020
 * 23:51
 **/
public class SetUtils {

    public static final String GRANTS_SEPARATOR = ",";

    public static Set getSetFromStringWithSeparator(String stringForSet){
        return Arrays.asList(stringForSet.trim().split(GRANTS_SEPARATOR))
                .stream().collect(Collectors.toSet());
    }
}
