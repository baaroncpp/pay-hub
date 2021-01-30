package com.jajjamind.payvault.core.repository;

import org.jooq.Record;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

public interface QueryResultPicker {

    default <T> T getResultSingle(Record result, Class< ? extends T> c) throws Exception{
        final BeanWrapper entity = new BeanWrapperImpl(c);
        PropertyDescriptor[] pds = entity.getPropertyDescriptors();

        Arrays.stream(pds).forEach(p -> {
            String displayName  = p.getDisplayName();
            if(!displayName.equalsIgnoreCase("class")) {
                Object value = result.getValue(displayName.toLowerCase());
                entity.setPropertyValue(displayName, value);
            }

        });

        return (T)entity.getWrappedInstance();
    }

    default MultiValueMap<String, Object> addCountParams(MultiValueMap<String, ?> map) {
        final MultiValueMap<String, Object> multivaluedHashMap = new LinkedMultiValueMap(map);
        multivaluedHashMap.add("showList", "false");
        multivaluedHashMap.add("offset", "0");
        multivaluedHashMap.add("limit", "5");
        return multivaluedHashMap;
    }
}
