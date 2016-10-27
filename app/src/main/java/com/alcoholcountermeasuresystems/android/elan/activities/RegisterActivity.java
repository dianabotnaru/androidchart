package com.alcoholcountermeasuresystems.android.elan.activities;

import android.content.Intent;
import android.os.Bundle;
import com.alcoholcountermeasuresystems.android.elan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 26/10/16.
 */

public class RegisterActivity extends BaseActivity {

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
        injectComponents();
    }

    @Override
    protected void injectComponents() {
    }
}
