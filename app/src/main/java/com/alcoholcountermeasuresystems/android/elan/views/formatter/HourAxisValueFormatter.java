package com.alcoholcountermeasuresystems.android.elan.views.formatter;

import com.alcoholcountermeasuresystems.android.elan.utils.ChartUtils;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by jordi on 03/11/16.
 */

public class HourAxisValueFormatter implements IAxisValueFormatter
{

    private DateTimeFormatter mDateTimeFormatter;
    private DateTime mDateTime;

    public HourAxisValueFormatter() {
        this.mDateTimeFormatter = DateTimeFormat.forPattern("HHa");
        this.mDateTime = new DateTime();
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        long convertedTimestamp = ChartUtils.getTimeStampFromXAxisValue(value);
        return getHour(convertedTimestamp);
    }

    @Override
    public int getDecimalDigits() {
        return 0;
    }

    private String getHour(long timestamp){
        try{
            mDateTime = new DateTime(timestamp*1000);
            return mDateTimeFormatter.print(mDateTime);
        }
        catch(Exception ex){
            return "xx";
        }
    }
}
