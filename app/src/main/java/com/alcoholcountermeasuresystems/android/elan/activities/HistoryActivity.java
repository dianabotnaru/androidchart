package com.alcoholcountermeasuresystems.android.elan.activities;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseActivity;
import com.alcoholcountermeasuresystems.android.elan.models.BAC;
import com.alcoholcountermeasuresystems.android.elan.utils.DateUtils;
import com.alcoholcountermeasuresystems.android.elan.views.BacEstimationChart;
import com.alcoholcountermeasuresystems.android.elan.views.DatePickUpLayout;
import com.alcoholcountermeasuresystems.android.elan.views.adapters.BacHistoryListAdapter;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jordi on 31/10/16.
 */

public class HistoryActivity extends BaseActivity {

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarTitleText;

    @BindView(R.id.layout_date_pickup)
    DatePickUpLayout mDatePickUpLayout;

    @BindView(R.id.linechart_history)
    BacEstimationChart mHistoryLineChart;

    @BindView(R.id.listView_history)
    ListView mHistoryListView;

    @BindString(R.string.history_title)
    String mTitleString;

    private Date mSelectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        initViews();
        initChart();
        initBacHistoryListView();
        setDatePickupListner();
    }

    private void initViews(){
        mToolbarTitleText.setText(mTitleString);
        mSelectDate = new Date();
        mDatePickUpLayout.setDateText(mSelectDate);
    }

    private void setDatePickupListner(){
        mDatePickUpLayout.setDatePickUpListener(new DatePickUpLayout.DatePickUpListener() {
            @Override
            public void onForwardDate() {
                mSelectDate = DateUtils.incrementDate(mSelectDate);
                mDatePickUpLayout.setDateText(mSelectDate);
            }

            @Override
            public void onBackDate() {
                mSelectDate = DateUtils.decrementDate(mSelectDate);
                mDatePickUpLayout.setDateText(mSelectDate);
            }
        });
    }

    private void initChart(){
        mHistoryLineChart.getLegend().setEnabled(false);
        ArrayList entries = new ArrayList();
        entries.add(new Entry(1451721600, 0.0040f));
        mHistoryLineChart.setLineChartDatas("",entries);
    }

    private List<BAC> getBacDatas(){
        //QA purpose
        List<BAC> bacdatas = new ArrayList<>();
        for (int i= 0; i<2;i++){
            BAC bac= new BAC();
            bac.setVolumeConsumption(110);
            bac.setPercentageConsumption(50);
            bac.setTimestamp(mSelectDate);
            bacdatas.add(bac);
        }
        return bacdatas;
    }

    private void initBacHistoryListView(){
        BacHistoryListAdapter bacListAdapter = new BacHistoryListAdapter(this);
        bacListAdapter.setItems(getBacDatas());
        mHistoryListView.setAdapter(bacListAdapter);
    }
}
