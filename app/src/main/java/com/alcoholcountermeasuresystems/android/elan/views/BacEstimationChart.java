package com.alcoholcountermeasuresystems.android.elan.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
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
        initXAxis();
        initYAxis();
        setLineChartDatas();
    }

    private void setLineChartDatas(){
        //Todo this lines for purpose
        ArrayList entries = new ArrayList();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 0.0040f));
        entries.add(new BarEntry(10f, 0.0080f));
        entries.add(new BarEntry(12f,0.0120f));
        entries.add(new BarEntry(14f,0.0160f));
        entries.add(new BarEntry(16f, 0.0200f));

        LineDataSet dataset = new LineDataSet(entries,"");
        dataset.setColor(ContextCompat.getColor(getContext(), R.color.blue));
        LineData data = new LineData(dataset);
        this.setData(data);
    }

    private void initXAxis(){
        XAxis xAxis = this.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),getContext().getResources().getString(R.string.font_opensans_regular));
        xAxis.setTypeface(tf);
        xAxis.setTextSize(12f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
    }

    private void initYAxis() {
        YAxis yAxis = this.getAxisLeft();
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),getContext().getResources().getString(R.string.font_opensans_regular));
        yAxis.setTypeface(tf);
        yAxis.setTextSize(12f);
        yAxis.setTextColor(Color.WHITE);
        YAxis yAxisRight = getAxisRight();
        yAxisRight.setEnabled(false);
    }
}
