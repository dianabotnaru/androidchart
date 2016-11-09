package com.alcoholcountermeasuresystems.android.elan.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.views.formatter.HourAxisValueFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;

import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * Created by jordi on 03/11/16.
 */

public class BacEstimationChart extends LineChart {

    public BacEstimationChart(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public BacEstimationChart(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        initChart();
    }

    public void setLineChartDatas(String dataSetLabel, ArrayList yValues){
        if (yValues.size()>0){
            LineDataSet dataset = new LineDataSet(yValues, "Test set");
            dataset.setColor(ContextCompat.getColor(getContext(), R.color.blue));
            dataset.setCircleColor(ContextCompat.getColor(getContext(), R.color.blue));
            dataset.setCircleColorHole(ContextCompat.getColor(getContext(), R.color.blue));
            dataset.setDrawValues(false);
            dataset.setLabel(dataSetLabel);
            LineData data = new LineData(dataset);
            this.setData(data);
        }
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
        xAxis.setDrawGridLines(true);
        xAxis.setValueFormatter(new HourAxisValueFormatter());
        xAxis.setAxisMinimum((float) 0);
        xAxis.setAxisMaximum((float) 6);
        xAxis.setLabelCount(6);
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
        legend.setTextColor(Color.WHITE);
        legend.setForm(Legend.LegendForm.CIRCLE);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"fonts/OpenSans-Regular.ttf");
        legend.setTypeface(tf);
        legend.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
        legend.setTextSize(12f);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
    }

    private void initDescription(){
        Description description = new Description();
        description.setText("");
        setDescription(description);
    }
}
