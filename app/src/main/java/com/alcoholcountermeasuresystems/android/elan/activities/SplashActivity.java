package com.alcoholcountermeasuresystems.android.elan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.alcoholcountermeasuresystems.android.elan.MainApplication;
import com.alcoholcountermeasuresystems.android.elan.R;

public class SplashActivity extends BaseActivity {

    Handler mHandler = new Handler();

    private boolean isRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHandler.postDelayed(() -> {
            if (isRegistered){
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
            else {
                startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
            }
            finish();
        }, 1000);
    }


    @Override
    protected void injectComponents() {
        MainApplication.getAppComponent().inject(this);
    }

}
