package com.alcoholcountermeasuresystems.android.elan.views.formatter;

import com.alcoholcountermeasuresystems.android.elan.utils.DateUtils;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jordi on 03/11/16.
 */

public class HourAxisValueFormatter implements IAxisValueFormatter
{

    private DateFormat mDataFormat;
    private Date mDate;

    public HourAxisValueFormatter() {
        this.mDataFormat = new SimpleDateFormat("HHa", Locale.ENGLISH);
        this.mDate = new Date();
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        long convertedTimestamp = (long) value;
        return getHour(convertedTimestamp);
    }

    @Override
    public int getDecimalDigits() {
        return 0;
    }

    private String getHour(long timestamp){
        try{
            DateTime dateTime = new DateTime(DateUtils.reference_timestamp*1000);
            mDate.setTime(DateUtils.reference_timestamp*1000);
            return mDataFormat.format(mDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }
}
