package com.alcoholcountermeasuresystems.android.elan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
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
    LineChart mBacLineChart;

    @BindString(R.string.bac_estimation_title)
    String mTitleString;

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
        setLineChartDatas();
    }

    private void initViews(){
        mToolbarTitleText.setText(mTitleString);
        mDateText.setText(DateFormat.getDateInstance().format(new Date()));
    }
//todo remove this function QA purpose
    private void setLineChartDatas(){
        ArrayList entries = new ArrayList();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));

        LineDataSet dataset = new LineDataSet(entries,"");
        LineData data = new LineData(dataset);
        mBacLineChart.setData(data);
    }
}
