package com.jajjamind.commons.time;

import java.util.*;
import java.text.*;

public abstract class TimestampUtils
{
    protected static final TimeZone UTC_TIME_ZONE;
    protected static final ThreadLocal<ThreadLevelInfo> FORMATTER;
    
    public static String getTimestamp(final String pattern) {
        final Date date = getCurrentDate();
        return getTimestamp(pattern, date);
    }
    
    public static String getTimestamp(final String pattern, final Date date) {
        return getTimestamp(pattern, TimestampUtils.UTC_TIME_ZONE, date);
    }
    
    public static String getTimestamp(final String pattern, final TimeZone timeZone) {
        final Date date = getCurrentDate();
        return getTimestamp(pattern, timeZone, date);
    }
    
    public static String getTimestamp(final String pattern, final TimeZone timeZone, final Date date) {
        final SimpleDateFormat formatter = getDateFormat(pattern, timeZone);
        return formatter.format(date);
    }
    
    public static Date getTime(final String pattern, final TimeZone timeZone, final String timestamp) {
        final SimpleDateFormat formatter = getDateFormat(pattern, timeZone);
        return formatter.parse(timestamp, new ParsePosition(0));
    }
    
    public static Date getTime(final String pattern, final String timestamp) {
        return getTime(pattern, TimestampUtils.UTC_TIME_ZONE, timestamp);
    }
    
    protected static Date getCurrentDate() {
        final Date date = TimestampUtils.FORMATTER.get().getDate();
        date.setTime(System.currentTimeMillis());
        return date;
    }
    
    protected static SimpleDateFormat getDateFormat(final String pattern, final TimeZone timeZone) {
        final SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setTimeZone(timeZone);
        return formatter;
    }
    
    static {
        UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");
        FORMATTER = new ThreadLocal<ThreadLevelInfo>() {
            @Override
            protected ThreadLevelInfo initialValue() {
                return new ThreadLevelInfo();
            }
        };
    }
    
    protected static final class ThreadLevelInfo
    {
        private final Date date;
        
        protected ThreadLevelInfo() {
            this.date = new Date();
        }
        
        public Date getDate() {
            return this.date;
        }
    }
}
