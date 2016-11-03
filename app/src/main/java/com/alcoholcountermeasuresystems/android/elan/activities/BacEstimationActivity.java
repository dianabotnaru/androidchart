package com.alcoholcountermeasuresystems.android.elan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseActivity;
import com.alcoholcountermeasuresystems.android.elan.views.BacEstimationChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 02/11/16.
 */

public class BacEstimationActivity extends BaseActivity {

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarTitleText;

    @BindView(R.id.text_date)
    TextView mDateText;

    @BindView(R.id.linchart_bac)
    BacEstimationChart mBacLineChart;

    @BindString(R.string.bac_estimation_title)
    String mTitleString;

    @BindString(R.string.bac_estimation_chart_legend)
    String mLegendString;

    @OnClick(R.id.button_add_drink)
    void onOkClicked() {
        startActivity(new Intent(BacEstimationActivity.this, AddDrinkActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac_estimation);
        ButterKnife.bind(this);
        initViews();
        initChart();//set chart datas
    }

    private void initViews(){
        mToolbarTitleText.setText(mTitleString);
        mDateText.setText(DateFormat.getDateInstance().format(new Date()));
    }

    //QA purpose set chart datas
    private void initChart(){
        ArrayList entries = new ArrayList();
        entries.add(new Entry(1451660400, 0));
        entries.add(new Entry(1451685600, 0.0040f));
        entries.add(new Entry(1451721600, 0.0080f));
        entries.add(new Entry(1451743200,0.0120f));
        entries.add(new Entry(1451761200,0.0160f));
        entries.add(new Entry(1451785200,0.0200f));
        mBacLineChart.setLineChartDatas(mLegendString,entries);
    }
}
