package com.jajjamind.payvault.core.utils;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import org.jboss.logging.Logger;
import org.springframework.beans.FatalBeanException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
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
                    //Same variable types here
                    if(sourceGetter.getReturnType().equals(setterClass) ) try {
                        target.getClass().getDeclaredMethod(activeTargetMethod.get().getName(), setterClass)
                                .invoke(target, sourceGetter.invoke(source, null));
                    } catch (NoSuchMethodException e) {
                        Logger.getLogger(BeanUtilsCustom.class).info("NoSuchMethodException while copying properties ");
                    }
                }
            }catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException  e) {
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

    public static void copyAuditedEntityProperties(AuditedEntity source,Object target) {

        try {
            target.getClass().getDeclaredMethod("setId",Long.class)
                    .invoke(source.getId());

            target.getClass().getDeclaredMethod("setCreatedOn",Long.class)
                    .invoke(source.getCreatedOn());

            target.getClass().getDeclaredMethod("setModifiedOn",Long.class)
                    .invoke(source.getModifiedOn());

        }catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            Logger.getLogger(BeanUtilsCustom.class).info("Failed to copy audited entity properties");
        }
    }


}
