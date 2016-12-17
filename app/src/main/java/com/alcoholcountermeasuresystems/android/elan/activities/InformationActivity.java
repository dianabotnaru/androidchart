package com.alcoholcountermeasuresystems.android.elan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
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
 * Created by jordi on 28/10/16.
 */

public class InformationActivity extends BaseActivity{

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarTitleText;

    @BindView(R.id.button_action)
    ImageButton mToolbarActionButton;

    @BindString(R.string.infomation_title)
    String mTitleString;

    @OnClick(R.id.button_discalimer)
    void onDisclaimerButtonPressed() {
        Intent intent = new Intent(InformationActivity.this, BacDisclaimerActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putBoolean(KeyIsComeInformation.toString(),false);
        intent.putExtras(mBundle);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews(){
        mToolbarTitleText.setText(mTitleString);
    }
}
