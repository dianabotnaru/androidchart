package com.alcoholcountermeasuresystems.android.elan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.views.ProfileInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 26/10/16.
 */

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.layout_firstname)
    ProfileInputLayout mFirstNameLayout;

    @BindView(R.id.layout_lastname)
    ProfileInputLayout mLastNameLayout;

    @BindView(R.id.layout_email)
    ProfileInputLayout mEmailLayout;

    @BindView(R.id.layout_language)
    ProfileInputLayout mLanguageLayout;

    @BindView(R.id.layout_country)
    ProfileInputLayout mCountryLayout;

    @BindView(R.id.layout_serial)
    ProfileInputLayout mSerialLayout;

    @BindView(R.id.layout_purchased)
    ProfileInputLayout mPurchasedLayout;

    @BindView(R.id.layout_location)
    ProfileInputLayout mLocationLayout;

    @OnClick(R.id.button_cancel)
    void onToolbarBackPressed() {
        startActivity(new Intent(RegisterActivity.this, SplashActivity.class));
    }

    @OnClick(R.id.button_register_activate)
    void onRegisterActivate() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initViews();
        injectComponents();
    }

    @Override
    protected void injectComponents() {
    }

    private void initViews(){
        mCountryLayout.setDropDownButtonPressedListener(new ProfileInputLayout.OnDropDownButtonPressedListener() {
            public void onDropDownButtonClicked() {
                Toast.makeText(getApplicationContext(), "country dropdown button clicked", Toast.LENGTH_SHORT).show();
                //do whatever you want to do when the event is performed.
            }
        });
    }
}
