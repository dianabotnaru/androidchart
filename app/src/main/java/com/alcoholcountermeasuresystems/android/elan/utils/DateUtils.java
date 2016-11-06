package com.alcoholcountermeasuresystems.android.elan.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jordi on 31/10/16.
 */

public class DateUtils {

    public static Date incrementDate(Date date) {
        Date newDate;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        newDate = c.getTime();
        return newDate;
    }

    public static Date decrementDate(Date date) {
        Date newDate;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -1);
        newDate = c.getTime();
        return newDate;
    }

    public static String getStringFromdate(Date date){
        String dateString;
        Format formatter = new SimpleDateFormat("EE MMM dd,yyyy hh:mm aa", Locale.ENGLISH);
        dateString = formatter.format(date);
        return dateString;
    }

    public static String getTimeStringFromdate(Date date){
        String timeString;
        Format formatter = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH);
        timeString = formatter.format(date);
        return timeString;
    }
}
