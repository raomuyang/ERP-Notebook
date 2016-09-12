package org.jufe.erp.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Raomengnan on 2016/6/25.
 */
public class DateTools {

    public static Date getDateBeforXDay(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    public static Date getDateBeforXDay(Timestamp d, int day) {
        Date date = new Date(d.getTime());
        return getDateBeforXDay(date,day);
    }

    public static Date getDateAfterXDay(Date d, int day){
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static Date getDateAfterXDay(Timestamp d, int day) {
        Date date = new Date(d.getTime());
        return getDateAfterXDay(date,day);
    }

    public static String dateFormat(Date date, String format){
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static Date string2Date(String dateStr){
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.parse(dateStr);
        } catch (ParseException e) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return df.parse(dateStr);
            } catch (ParseException e1) {
                return null;
            }
        }

    }

}
