package com.yangy.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期相关工具类
 *
 * @author yang yang
 * @create 2018/10/31
 */
public class DateUtils {

    private static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static Date getSys() {
        return new Date();
    }

    public static long getSysMs() {
        return System.currentTimeMillis();
    }

    public static String getTimStrByFormat() {
        return getTimStrByFormat(getSys(), DEFAULT_PATTERN);
    }

    public static String getTimStrByFormat(Date date) {
        return getTimStrByFormat(date, DEFAULT_PATTERN);
    }

    public static String getTimStrByFormat(long _timestamp) {
        return getTimStrByFormat(_timestamp, DEFAULT_PATTERN);
    }

    public static String getTimStrByFormat(String pattern) {
        return getTimStrByFormat(getSys(), pattern);
    }

    public static String getTimStrByFormat(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String getTimStrByFormat(long _timestamp, String pattern) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(_timestamp);
        Date date = instance.getTime();
        return getTimStrByFormat(date, pattern);
    }

    public static long getTimMs(String timeStr) throws ParseException {
        DateFormat instance = SimpleDateFormat.getInstance();
        Date parse = instance.parse(timeStr);
        return parse.getTime();
    }

    public static long getHourLater() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        calendar.set(Calendar.HOUR, hour + 1);
        return calendar.getTimeInMillis();
    }


    public static void main(String[] args) throws ParseException {

        long hourLater = getHourLater();
        String timStrByFormat = getTimStrByFormat();
        System.out.println(timStrByFormat);
        System.out.println(getTimStrByFormat(hourLater));
    }
}