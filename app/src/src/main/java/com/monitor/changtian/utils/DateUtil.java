package com.monitor.changtian.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ken on 2018/5/18.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class DateUtil  {
    private static SimpleDateFormat sf = null;
    /*将字符串转为时间戳*/
    public static long getStringToDate(String time,String tpl) {
        sf = new SimpleDateFormat(tpl);
        Date date = new Date();
        try{
            date = sf.parse(time);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
}
