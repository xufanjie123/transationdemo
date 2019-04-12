package com.fanjiexu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cui on 2016/11/9.
 */
public class TimeUtil {
    /**
     * 毫秒转化成 5:30
     *
     * @param duration
     * @return
     */
    public static String ms2MS(int duration) {
        int s = duration / 1000;
        int f = s / 60;
        s = s % 60;
        String res = f + ":";
        if (s < 10) {
            res = res + "0" + s;
        } else {
            res = res + s;
        }
        return res;
    }

    /**
     * 字符串转化为Date  yy-MM-dd
     *
     * @param date
     * @return
     */
    public static Date string2Date(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 判断字符串是否是yyyy-MM-dd格式的时间
     *
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
        boolean flag = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            flag = false;
        }
        return flag;
    }

    public static String getNowDateTime() {
        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date date = new Date(l);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static String getNowDate() {
        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date date = new Date(l);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static boolean isSameDate(Date date1, Date date2){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date1).equals(dateFormat.format(date2));
    }
}
