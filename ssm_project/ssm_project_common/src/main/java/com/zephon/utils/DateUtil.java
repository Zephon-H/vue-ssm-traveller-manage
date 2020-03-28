package com.zephon.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.utils
 * 日期工具类
 */
public class DateUtil {
    /**
     * 日期转字符串
     */
    public static String date2String(Date date, String pattern){
        SimpleDateFormat smf = new SimpleDateFormat(pattern);
        String format = smf.format(date);
        return format;
    }
    /**
     * 字符串转日期
     */
    public static Date string2Date(String str,String pattern) throws ParseException {
        SimpleDateFormat smf = new SimpleDateFormat(pattern);
        Date date = smf.parse(str);
        return date;
    }
}
