package com.alcoholcountermeasuresystems.android.elan.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.MainApplication;
import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.BacDisclaimerActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.ContactUsActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.InformationActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.RegisterActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseInjectableActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseInjectableActivity {

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarTitleText;

    @BindView(R.id.button_register)
    Button mRegisterButton;

    @BindView(R.id.layout_breath_test)
    RelativeLayout mBreathTestLayout;

    @OnClick(R.id.button_register)
    void onRegisterButtonPressed() {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        //for QA purposes
        notRegistered=(!notRegistered);
        initRegisterState(notRegistered);
    }

    @OnClick(R.id.layout_information)
    void onInformationPressed() {
        startActivity(new Intent(MainActivity.this, InformationActivity.class));
    }

    @OnClick(R.id.layout_bac_estimation)
    void onBacEstimationPressed() {
        startActivity(new Intent(MainActivity.this, BacDisclaimerActivity.class));
    }

    @BindString(R.string.home_title)
    String mTitleString;

    @BindString(R.string.home_registered_button_title)
    String mRegisteredButtonString;

    @BindString(R.string.home_notregistered_button_title)
    String mNotRegisteredButtonString;


    private boolean notRegistered; //QA purpose

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews(){
        initRegisterState(true);
        initToolbar();
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbarTitleText.setText(mTitleString);
    }

    private void initRegisterState(boolean notRegistered){
        mBreathTestLayout.setEnabled(!notRegistered);
        if (notRegistered){
            mRegisterButton.setText(mNotRegisteredButtonString);
        }else {
            mRegisterButton.setText(mRegisteredButtonString);
        }
        mRegisterButton.setEnabled(notRegistered);
    }       
    @Override
    protected void injectComponents() {
        MainApplication.getAppComponent().inject(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_dedisclaimer) {
            return true;
        }
        else if (id == R.id.action_contactus) {
            startActivity(new Intent(MainActivity.this, ContactUsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
