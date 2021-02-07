package com.jajjamind.commons.utils;

public class UUIDUtil {
    public static String getUUID() {
        return java.util.UUID.randomUUID().toString().replaceAll("-", "");
    }
}
