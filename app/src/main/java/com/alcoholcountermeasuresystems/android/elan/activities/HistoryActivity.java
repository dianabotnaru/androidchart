package com.alcoholcountermeasuresystems.android.elan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.MainApplication;
import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseInjectableActivity;
import com.alcoholcountermeasuresystems.android.elan.managers.RealmStore;
import com.alcoholcountermeasuresystems.android.elan.models.BAC;
import com.alcoholcountermeasuresystems.android.elan.views.BacEstimationChart;
import com.alcoholcountermeasuresystems.android.elan.views.DatePickUpLayout;
import com.alcoholcountermeasuresystems.android.elan.views.adapters.BacHistoryListAdapter;
import com.github.mikephil.charting.data.Entry;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.alcoholcountermeasuresystems.android.elan.data.enums.BundleKey.KeyBac;
import static com.alcoholcountermeasuresystems.android.elan.data.enums.BundleKey.KeyIsComeHistory;

/**
 * Created by jordi on 31/10/16.
 */

public class HistoryActivity extends BaseInjectableActivity {

    @Inject
    RealmStore mRealmStore;

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

    List<BAC> mHistoryBacs;
    private BacHistoryListAdapter mBacListAdapter;

    private DateTime mSelectDate;

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

    @Override
    protected void injectComponents() {
        MainApplication.getAppComponent().inject(this);
    }

    private void initViews(){
        mToolbarTitleText.setText(mTitleString);
        mSelectDate = new DateTime();
        mDatePickUpLayout.setDateText(mSelectDate);
    }

    private void setDatePickupListner(){
        mDatePickUpLayout.setDatePickUpListener(new DatePickUpLayout.DatePickUpListener() {
            @Override
            public void onForwardDate() {
                mSelectDate = mSelectDate.plusDays(1);
                mDatePickUpLayout.setDateText(mSelectDate);
                refreshListView();
            }

            @Override
            public void onBackDate() {
                mSelectDate = mSelectDate.minusDays(1);
                mDatePickUpLayout.setDateText(mSelectDate);
                refreshListView();
            }
        });
    }

    private void initChart(){
        mHistoryLineChart.getLegend().setEnabled(false);
        ArrayList entries = new ArrayList();
        entries.add(new Entry(1451721600, 0.0040f));
//        mHistoryLineChart.setLineChartDatas("",entries);
    }

    private void getBacDatas(){
        mHistoryBacs = mRealmStore.retrieveBacsforDay(mSelectDate);
    }

    private void initBacHistoryListView(){
        getBacDatas();
        mBacListAdapter = new BacHistoryListAdapter(this);
        mBacListAdapter.setItems(mHistoryBacs);
        mHistoryListView.setAdapter(mBacListAdapter);
        mHistoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                BAC bac = mHistoryBacs.get(position);
                Intent intent = new Intent(HistoryActivity.this, AddDrinkActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putParcelable(KeyBac.toString(), bac);
                mBundle.putBoolean(KeyIsComeHistory.toString(),true);
                intent.putExtras(mBundle);
                startActivity(intent);
                finish();
            }
        });
    }

    private void refreshListView(){
        getBacDatas();
        mBacListAdapter.setItems(mHistoryBacs);
        mBacListAdapter.notifyDataSetChanged();
    }
}
