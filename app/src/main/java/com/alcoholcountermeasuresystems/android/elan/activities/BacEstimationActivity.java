package com.alcoholcountermeasuresystems.android.elan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.MainApplication;
import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseInjectableActivity;
import com.alcoholcountermeasuresystems.android.elan.managers.RealmStore;
import com.alcoholcountermeasuresystems.android.elan.models.BAC;
import com.alcoholcountermeasuresystems.android.elan.views.BacEstimationChart;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 02/11/16.
 */

public class BacEstimationActivity extends BaseInjectableActivity {

    @Inject
    RealmStore mRealmStore;

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarTitleText;

    @BindView(R.id.text_date)
    TextView mDateText;

    @BindView(R.id.linechart_bac)
    BacEstimationChart mBacLineChart;

    @BindString(R.string.bac_estimation_title)
    String mTitleString;

    @BindString(R.string.bac_estimation_chart_legend)
    String mLegendString;

    @OnClick(R.id.button_add_drink)
    void onOkClicked() {
        startActivity(new Intent(BacEstimationActivity.this, AddDrinkActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac_estimation);
        ButterKnife.bind(this);
        initViews();
        initChart();//set chart datas
    }

    @Override
    protected void injectComponents() {
        MainApplication.getAppComponent().inject(this);
    }

    private void initViews(){
        mToolbarTitleText.setText(mTitleString);
        mDateText.setText(new SimpleDateFormat("EE").format(new Date())+", "+DateFormat.getDateInstance().format(new Date()));
    }

    private void initChart(){
        List<BAC> Bacs = mRealmStore.retrieveBacs(new DateTime());
        mBacLineChart.setLineChartDatas(mLegendString,Bacs);
    }
}
