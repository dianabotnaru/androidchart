package com.alcoholcountermeasuresystems.android.elan.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseActivity;
import com.alcoholcountermeasuresystems.android.elan.utils.DateUtils;
import com.alcoholcountermeasuresystems.android.elan.views.DatePickUpLayout;

import java.util.Date;

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

    @BindString(R.string.history_title)
    String mTitleString;

    private Date mSelectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        initViews();
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

}
