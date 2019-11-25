package com.monitor.changtian.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.PatternSyntaxException;

/**
 * @author HuangWenwei
 * @date 2014年10月9日
 */
public class TimeZoneUtil {

    /**
     * 判断用户的设备时区是否为东八区（中国） 2014年7月31日
     *
     * @return
     */
    public static boolean isInEasternEightZones() {
        boolean defaultVaule = true;
        if (TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08"))
            defaultVaule = true;
        else
            defaultVaule = false;
        return defaultVaule;
    }

    /**
     * 根据不同时区，转换时间 2014年7月31日
     *
     * @param oldZone
     * @return
     */
    public static Date transformTime(Date date, TimeZone oldZone, TimeZone newZone) {
        Date finalDate = null;
        if (date != null) {
            int timeOffset = oldZone.getOffset(date.getTime())
                    - newZone.getOffset(date.getTime());
            finalDate = new Date(date.getTime() - timeOffset);
        }
        return finalDate;
    }

    /**
     * 根据时间获取年龄。
     *
     * @param birthDate
     * @return
     */

    public static int getAge(Date birthDate) {

        if (birthDate == null)
            throw new
                    RuntimeException("出生日期不能为null");
        int age = 0;
        Date now = new Date();

        SimpleDateFormat format_y = new
                SimpleDateFormat("yyyy");
        SimpleDateFormat format_M = new
                SimpleDateFormat("MM");

        String birth_year =
                format_y.format(birthDate);
        String this_year =
                format_y.format(now);

        String birth_month =
                format_M.format(birthDate);
        String this_month =
                format_M.format(now);

        // 初步，估算
        age =
                Integer.parseInt(this_year) - Integer.parseInt(birth_year);

        // 如果未到出生月份，则age - 1
        if
                (this_month.compareTo(birth_month) < 0)
            age -=
                    1;
        if (age <
                0)
            age =
                    0;
        return age;
    }

    public static int getage(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            } else {
                age--;
            }
        }
        return age;
    }

    /**
     * 根据传入的参数计算出前多少天的数据
     *
     * @param DataTime
     * @return
     */

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static SimpleDateFormat sdfday = new SimpleDateFormat("yyyy-MM-dd");

    public static String setStatetime(int DataTime) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, DataTime);
        Date monday = c.getTime();
        String preMonday = sdf.format(monday);
        return preMonday;
    }

    public static String setDrugsStatetime(int index, String dataTime) {
        String preMonday = null;
        try {
            Date dates = sdfday.parse(dataTime);
            Calendar c = Calendar.getInstance();
            c.setTime(dates);
            c.add(Calendar.DATE, index);
            Date monday = c.getTime();
            preMonday = sdf.format(monday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return preMonday;
    }


    //邮箱验证
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";

//        if (TextUtils.isEmpty(strPattern)) {
//            return false;
//        } else {
        return strEmail.matches(strPattern);
//        }
    }

    private static SimpleDateFormat sf = null;

    /*将字符串转为时间戳*/
    public static long getStringToDate(String time, String tpl) {
        sf = new SimpleDateFormat(tpl);
        Date date = null;
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }


    public static String stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母、和空格和汉字
        String regEx = "[a-zA-Z\\u4E00-\\u9FA5 ]+";
        return str != null ? (str.matches(regEx) ? str : "") : "";
    }


    public static String timeAddDay() {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        return sf.format(c.getTime()) + "";
    }

    /*
   * 将时间戳转换为时间
   */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String testtime(Long times) {
        Date date = new Date(times);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(date);
    }



    /**
     * 计算剩余时间
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    private static Calendar calS= Calendar.getInstance();

    public static String remainDateToString(String startDateStr ){

        java.util.Date startDate = null;
        java.util.Date endDate= null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        try {
            endDate = new Date();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        calS.setTime(startDate);
        int startY = calS.get(Calendar.YEAR);
        int startM = calS.get(Calendar.MONTH);
        int startD = calS.get(Calendar.DATE);
        int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        calS.setTime(endDate);
        int endY = calS.get(Calendar.YEAR);
        int endM = calS.get(Calendar.MONTH);
        //处理2011-01-10到2011-01-10，认为服务为一天
        int endD = calS.get(Calendar.DATE);
        int endDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        StringBuilder sBuilder = new StringBuilder();
        if (endDate.compareTo(startDate)<0) {
            return sBuilder.append("过期").toString();
        }
        int lday = endD-startD;
        if (lday<0) {
            endM = endM -1;
            lday = startDayOfMonth+ lday;
        }
        //处理天数问题，如：2011-01-01 到	2013-12-31 	2年11个月31天     实际上就是3年
        if (lday == endDayOfMonth) {
            endM = endM+1;
            lday =0;
        }
        int mos = (endY - startY)*12 + (endM- startM);
        int lyear = mos/12;
        int lmonth = mos%12;
        if (lyear >0) {
            sBuilder.append(lyear+"年");
        }
        if (lmonth > 0) {
            sBuilder.append(lmonth+"月");
        }
        if (lday >0 ) {
            sBuilder.append(lday+"天");
        }
        return sBuilder.toString();
    }


}
