package com.alcoholcountermeasuresystems.android.elan.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.models.BAC;
import com.alcoholcountermeasuresystems.android.elan.utils.ChartUtils;
import com.alcoholcountermeasuresystems.android.elan.views.formatter.HourAxisValueFormatter;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
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
        initLegend();
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
        xAxis.setValueFormatter(new HourAxisValueFormatter());
        xAxis.setAxisMinimum((float) 0);
        xAxis.setAxisMaximum((float) 6);
        xAxis.setLabelCount(0);
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

    private void initLegend(){
        Legend legend = getLegend();
    }

    private void initDescription(){
        Description description = new Description();
        description.setText("");
        setDescription(description);
    }

    private ArrayList getEntriesForHistory(List<BAC> bacs){
        ArrayList  yValues = new ArrayList();
        for (BAC bac : bacs) {
            yValues.add(new Entry(ChartUtils.getXAxisValueFromTimeStamp(bac.getTimeStamp()),(float) bac.getPercentageConsumption()/100 ));
        }
        return yValues;
    }

    private ArrayList<String> getXvaluesForHistory(List<BAC> bacs){
        ArrayList<String>  xValues = new ArrayList<String>();
        for (BAC bac : bacs) {
            xValues.add(String.valueOf(ChartUtils.getXAxisValueFromTimeStamp(bac.getTimeStamp())));
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
            ScatterData chartData = new ScatterData(dataset) ;
            this.setData(chartData);
        }
    }

}
