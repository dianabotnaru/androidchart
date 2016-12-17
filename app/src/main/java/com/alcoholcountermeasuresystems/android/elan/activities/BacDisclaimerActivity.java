package com.alcoholcountermeasuresystems.android.elan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.alcoholcountermeasuresystems.android.elan.data.enums.BundleKey.KeyIsComeInformation;

/**
 * Created by jordi on 30/10/16.
 */

public class BacDisclaimerActivity extends BaseActivity {

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarTitleText;

    @BindString(R.string.bac_disclaimer_title)
    String mTitleString;

    @BindView(R.id.button_cancel)
    TextView mCancelButton;

    @BindView(R.id.button_accept)
    TextView mAcceptButton;

    private boolean mIsFromInformation;

    @OnClick(R.id.button_cancel)
    void onCancelButtonPressed() {
        onBackPressed();
    }

    @OnClick(R.id.button_accept)
    void onAcceptButtonPressed() {
        startActivity(new Intent(BacDisclaimerActivity.this, BacEstimatorActivity.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac_disclaimer);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        mIsFromInformation = bundle.getBoolean(KeyIsComeInformation.toString());
        initViews();
    }

    private void initViews(){
        mToolbarTitleText.setText(mTitleString);
        if (mIsFromInformation)
            hiddenButtons();
    }

    private void hiddenButtons(){
        mAcceptButton.setVisibility(View.GONE);
        mCancelButton.setVisibility(View.GONE);
    }
}
