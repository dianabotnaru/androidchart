package com.alcoholcountermeasuresystems.android.elan.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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

    public static String getTimeStringFromTimeStamp(long timeStamp){
        DateTime dateTime = new DateTime(timeStamp*1000);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm a");
        return formatter.print(dateTime);
    }

    public static String getDateStringFromDateTime(DateTime dateTime ){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("EE MMM dd,yyyy");
        return formatter.print(dateTime);
    }

}
