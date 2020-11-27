package com.jajjamind.commons.utils;

import com.jajjamind.commons.text.StringUtil;

public abstract class PhonenumberUtil
{
    private static final char COLON = ':';
    private static final char PLUS = '+';
    private static final char SINGLE_ZERO = '0';
    
    public static String trimPhonenumber(String phonenumber) {
        if (StringUtil.isEmpty(phonenumber)) {
            return phonenumber;
        }
        phonenumber = phonenumber.trim();
        final int index = phonenumber.indexOf(58);
        phonenumber = phonenumber.substring(index + 1);
        StringBuilder builder = new StringBuilder(phonenumber);
        for (boolean isPlusChar = builder.charAt(0) == '+'; isPlusChar; isPlusChar = (builder.charAt(0) == '+')) {
            builder = builder.delete(0, 1);
        }
        for (boolean isZeroChar = builder.charAt(0) == '0'; isZeroChar; isZeroChar = (builder.charAt(0) == '0')) {
            builder = builder.delete(0, 1);
        }
        return builder.toString();
    }
    
    public static String trimColonfromPhone(String phonenumber) {
        if (StringUtil.isEmpty(phonenumber)) {
            return phonenumber;
        }
        phonenumber = phonenumber.trim();
        final int index = phonenumber.indexOf(58);
        phonenumber = phonenumber.substring(index + 1);
        return phonenumber;
    }
    
    public static String trimPlusFromPhone(String phonenumber) {
        if (StringUtil.isEmpty(phonenumber)) {
            return phonenumber;
        }
        phonenumber = phonenumber.trim();
        StringBuilder builder = new StringBuilder(phonenumber);
        for (boolean isPlusChar = builder.charAt(0) == '+'; isPlusChar; isPlusChar = (builder.charAt(0) == '+')) {
            builder = builder.delete(0, 1);
        }
        return builder.toString();
    }
    
    public static String trimZeroFromPhone(String phonenumber) {
        if (StringUtil.isEmpty(phonenumber)) {
            return phonenumber;
        }
        phonenumber = phonenumber.trim();
        StringBuilder builder = new StringBuilder(phonenumber);
        for (boolean isZeroChar = builder.charAt(0) == '0'; isZeroChar; isZeroChar = (builder.charAt(0) == '0')) {
            builder = builder.delete(0, 1);
        }
        return builder.toString();
    }
}
