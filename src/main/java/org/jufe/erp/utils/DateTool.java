package org.jufe.erp.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Raomengnan on 2016/6/25.
 */
public class DateTool {

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
}
