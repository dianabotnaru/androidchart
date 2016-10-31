package com.alcoholcountermeasuresystems.android.elan.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jordi on 31/10/16.
 */

public class HistoryActivity extends BaseActivity {

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarTitleText;

    @BindString(R.string.history_title)
    String mTitleString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews(){
        mToolbarTitleText.setText(mTitleString);
    }
}
