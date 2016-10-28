package com.alcoholcountermeasuresystems.android.elan.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.MainApplication;
import com.alcoholcountermeasuresystems.android.elan.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindString(R.string.home_title)
    String mTitleString;

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarTitleText;

    @OnClick(R.id.button_action)
    void onToolbarActionPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    void initViews(){
        mToolbarTitleText.setText(mTitleString);
    }
    @Override
    protected void injectComponents() {
        MainApplication.getAppComponent().inject(this);
    }

}
