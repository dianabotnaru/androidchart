package com.alcoholcountermeasuresystems.android.elan.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 30/10/16.
 */

public class BacEstimatorActivity extends BaseActivity {
    @BindView(R.id.text_toolbar_title)
    TextView mToolbarTitleText;

    @BindView(R.id.text_toolbar_next)
    TextView mToolbarNextText;

    @BindString(R.string.bac_disclaimer_title)
    String mTitleString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac_estimator);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews(){
        mToolbarTitleText.setText(mTitleString);
        mToolbarNextText.setVisibility(View.VISIBLE);
    }

}
