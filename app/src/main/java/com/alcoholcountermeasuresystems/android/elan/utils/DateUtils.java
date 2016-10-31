package com.alcoholcountermeasuresystems.android.elan.utils;

import java.util.Calendar;
import java.util.Date;

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

}
