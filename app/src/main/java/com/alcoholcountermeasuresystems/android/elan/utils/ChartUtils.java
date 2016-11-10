package com.alcoholcountermeasuresystems.android.elan.utils;

import org.joda.time.DateTime;

/**
 * Created by jordi on 09/11/16.
 */

public class ChartUtils {

    public static long getUnitTimeStampforXAxis(){
        DateTime nowDate = new DateTime();
        long miniumTimeStamp = nowDate.minusHours(12).getMillis() / 1000;
        long maxiumTimeStamp = nowDate.plusHours(12).getMillis() / 1000;
        long intevalTimeStamp = (maxiumTimeStamp - miniumTimeStamp)/6;
        return intevalTimeStamp;
    }

    public static long getTimeStampforOneHour(){
        return getUnitTimeStampforXAxis()/4;
    }

    public static long getMiniumXAxisValue(){
        DateTime nowDate = new DateTime();
        long miniumTimeStamp = nowDate.minusHours(12).getMillis() / 1000;
        return miniumTimeStamp;
    }
}
