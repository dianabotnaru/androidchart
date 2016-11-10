package com.alcoholcountermeasuresystems.android.elan.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.models.BAC;
import com.alcoholcountermeasuresystems.android.elan.utils.DateUtils;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordi on 10/11/16.
 */

public class BacHistoryChart extends ScatterChart {

    public BacHistoryChart(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public BacHistoryChart(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        initChart();
    }
    public void initChart(){
        initXAxis();
        initYAxis();
        initDescription();
    }

    private void initXAxis(){
        XAxis xAxis = this.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"fonts/OpenSans-Regular.ttf");
        xAxis.setTypeface(tf);
        xAxis.setTextSize(12f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
    }

    private void initYAxis() {
        YAxis yAxis = this.getAxisLeft();
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"fonts/OpenSans-Regular.ttf");
        yAxis.setTypeface(tf);
        yAxis.setTextSize(12f);
        yAxis.setTextColor(Color.WHITE);
        YAxis yAxisRight = getAxisRight();
        yAxisRight.setEnabled(false);
    }

    private void initDescription(){
        setDescription("");
    }

    private ArrayList<Entry> getEntriesForHistory(List<BAC> bacs){
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        int i = 0;
        for (BAC bac : bacs) {
            yValues.add(new Entry((float) bac.getPercentageConsumption()/100,i));
            i++;
        }
        return yValues;
    }

    private ArrayList<String> getXvaluesForHistory(List<BAC> bacs){
        ArrayList<String>  xValues = new ArrayList<String>();
        for (BAC bac : bacs) {
            xValues.add(DateUtils.getAxisTimeStringFromTimeStamp(bac.getTimeStamp()));
        }
        return xValues;
    }

    public void setChartDatas(List<BAC> bacs){
        if (bacs.size()>0){
            ScatterDataSet dataset = new ScatterDataSet(getEntriesForHistory(bacs), "");
            dataset.setColor(ContextCompat.getColor(getContext(), R.color.blue));
            dataset.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
            dataset.setScatterShapeSize(7);
            dataset.setScatterShapeHoleColor(ContextCompat.getColor(getContext(), R.color.blue));
            dataset.setDrawValues(false);
            ScatterData chartData = new ScatterData(getXvaluesForHistory(bacs),dataset) ;
            this.setData(chartData);
        }else {
            clear();
        }
        invalidate();
    }
}
