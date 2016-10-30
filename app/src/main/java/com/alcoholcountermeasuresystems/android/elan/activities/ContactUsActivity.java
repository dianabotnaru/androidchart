package com.alcoholcountermeasuresystems.android.elan.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.BuildConfig;
import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jordi on 30/10/16.
 */

public class ContactUsActivity extends BaseActivity {

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarTitleText;

    @BindString(R.string.menu_contactus)
    String mTitleString;

    @BindView(R.id.text_version)
    TextView mVesionText;

    @BindView(R.id.text_contact)
    TextView mContactNumeberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews(){
        mToolbarTitleText.setText(mTitleString);
        getAppVersion();
        getContactPhoneNumber();
    }

    private void getAppVersion() {
        String versionName = BuildConfig.VERSION_NAME;
        mVesionText.setText(versionName);
    }

    private void getContactPhoneNumber() {
        // Todo remove this line
        String phoneNumber = "14166193500";//QA purpose
        mContactNumeberText.setText(phoneNumber);
    }

}
