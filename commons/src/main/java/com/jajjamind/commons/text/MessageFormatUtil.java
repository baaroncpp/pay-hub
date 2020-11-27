/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jajjamind.commons.text;


import java.util.*;

public abstract class MessageFormatUtil
{
    public static String arrayFormat(final String messagePattern, final Object[] argArray) {
        final boolean isInValidParameter = null == messagePattern || null == argArray || argArray.length == 0;
        if (isInValidParameter) {
            return messagePattern;
        }
        int i = 0;
        final StringBuilder sbuf = new StringBuilder(messagePattern.length() + 50);
        int index = -1;
        int l = 0;
        while (l < argArray.length) {
            index = messagePattern.indexOf("{}", i);
            if (index == -1) {
                if (i == 0) {
                    return messagePattern;
                }
                sbuf.append(messagePattern.substring(i, messagePattern.length()));
                return sbuf.toString();
            }
            else {
                if (isEscapedDelimeter(messagePattern, index)) {
                    if (!isDoubleEscaped(messagePattern, index)) {
                        sbuf.append(messagePattern.substring(i, index - 1));
                        sbuf.append('{');
                        i = index + 1;
                        continue;
                    }
                    sbuf.append(messagePattern.substring(i, index - 1));
                    deeplyAppendParameter(sbuf, argArray[l], new HashMap(10));
                    i = index + 2;
                }
                else {
                    sbuf.append(messagePattern.substring(i, index));
                    deeplyAppendParameter(sbuf, argArray[l], new HashMap(10));
                    i = index + 2;
                }
                ++l;
            }
        }
        sbuf.append(messagePattern.substring(i, messagePattern.length()));
        return sbuf.toString();
    }
    
    private static boolean isEscapedDelimeter(final String messagePattern, final int delimeterStartIndex) {
        if (delimeterStartIndex == 0) {
            return false;
        }
        final char potentialEscape = messagePattern.charAt(delimeterStartIndex - 1);
        return potentialEscape == '\\';
    }
    
    private static boolean isDoubleEscaped(final String messagePattern, final int delimeterStartIndex) {
        return delimeterStartIndex >= 2 && messagePattern.charAt(delimeterStartIndex - 2) == '\\';
    }
    
    private static void deeplyAppendParameter(final StringBuilder sbuf, final Object o, final Map seenMap) {
        if (o == null) {
            sbuf.append("null");
            return;
        }
        if (!o.getClass().isArray()) {
            safeObjectAppend(sbuf, o);
        }
        else if (o instanceof boolean[]) {
            booleanArrayAppend(sbuf, (boolean[])o);
        }
        else if (o instanceof byte[]) {
            byteArrayAppend(sbuf, (byte[])o);
        }
        else if (o instanceof char[]) {
            charArrayAppend(sbuf, (char[])o);
        }
        else if (o instanceof short[]) {
            shortArrayAppend(sbuf, (short[])o);
        }
        else if (o instanceof int[]) {
            intArrayAppend(sbuf, (int[])o);
        }
        else if (o instanceof long[]) {
            longArrayAppend(sbuf, (long[])o);
        }
        else if (o instanceof float[]) {
            floatArrayAppend(sbuf, (float[])o);
        }
        else if (o instanceof double[]) {
            doubleArrayAppend(sbuf, (double[])o);
        }
        else {
            objectArrayAppend(sbuf, (Object[])o, seenMap);
        }
    }
    
    private static void safeObjectAppend(final StringBuilder sbuf, final Object o) {
        try {
            final String oAsString = o.toString();
            sbuf.append(oAsString);
        }
        catch (Throwable t) {
            sbuf.append("[FAILED toString()]");
        }
    }
    
    private static void objectArrayAppend(final StringBuilder sbuf, final Object[] a, final Map seenMap) {
        sbuf.append('[');
        if (!seenMap.containsKey(a)) {
            seenMap.put(a, null);
            for (int len = a.length, i = 0; i < len; ++i) {
                deeplyAppendParameter(sbuf, a[i], seenMap);
                if (i != len - 1) {
                    sbuf.append(", ");
                }
            }
            seenMap.remove(a);
        }
        else {
            sbuf.append("...");
        }
        sbuf.append(']');
    }
    
    private static void booleanArrayAppend(final StringBuilder sbuf, final boolean[] a) {
        sbuf.append('[');
        for (int len = a.length, i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }
    
    private static void byteArrayAppend(final StringBuilder sbuf, final byte[] a) {
        sbuf.append('[');
        for (int len = a.length, i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }
    
    private static void charArrayAppend(final StringBuilder sbuf, final char[] a) {
        sbuf.append('[');
        for (int len = a.length, i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }
    
    private static void shortArrayAppend(final StringBuilder sbuf, final short[] a) {
        sbuf.append('[');
        for (int len = a.length, i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }
    
    private static void intArrayAppend(final StringBuilder sbuf, final int[] a) {
        sbuf.append('[');
        for (int len = a.length, i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }
    
    private static void longArrayAppend(final StringBuilder sbuf, final long[] a) {
        sbuf.append('[');
        for (int len = a.length, i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }
    
    private static void floatArrayAppend(final StringBuilder sbuf, final float[] a) {
        sbuf.append('[');
        for (int len = a.length, i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }
    
    private static void doubleArrayAppend(final StringBuilder sbuf, final double[] a) {
        sbuf.append('[');
        for (int len = a.length, i = 0; i < len; ++i) {
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
        }
        sbuf.append(']');
    }
}
