package com.jajjamind.payvault.core.utils;

import org.springframework.beans.FatalBeanException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author akena
 * 09/02/2021
 * 18:29
 **/
public class BeanUtilsCustom {

    public static void copyProperties(Object source, Object target) {
        final List<Method> sourceGetters = Arrays.stream(source.getClass().getMethods()).filter(t -> t.getName().startsWith("get") || t.getName().startsWith("is"))
                .collect(Collectors.toList());
        final List<Method> targetSetters = Arrays.stream(target.getClass().getMethods()).filter(t -> t.getName().startsWith("set")).collect(Collectors.toList());


        for (Method sourceGetter : sourceGetters) {
            final String sourceSetterName = getSetterFromGetter(sourceGetter.getName());
            try{
                Optional<Method> activeTargetMethod = targetSetters.stream()
                        .filter(t -> t.getName().equals(sourceSetterName)).findAny();
                if(activeTargetMethod.isPresent())
                {
                    Class setterClass = activeTargetMethod.get().getParameterTypes()[0];
                    if(sourceGetter.getReturnType().equals(setterClass) ){
                        //Same variable types here
                        target.getClass().getDeclaredMethod(activeTargetMethod.get().getName(),setterClass)
                                .invoke(target,sourceGetter.invoke(source,null));

                    }
                }
            }catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException  | NoSuchMethodException e) {
                throw new FatalBeanException(
                        "Could not copy property '" + sourceSetterName + "' from source to target", e); }
        }

    }



    private static String getSetterFromGetter(String getter){
        if(getter.startsWith("get"))
            return getter.replaceFirst("get","set");

        if(getter.startsWith("is"))
            return getter.replaceFirst("is","set");

        return getter;
    }

}
