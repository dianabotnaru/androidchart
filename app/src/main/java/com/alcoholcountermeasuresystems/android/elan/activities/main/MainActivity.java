package com.alcoholcountermeasuresystems.android.elan.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment2;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.MainApplication;
import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.AddDrinkActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.BacDisclaimerActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.BacEstimationActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.ContactUsActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.HistoryActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.InformationActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.RegisterActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseInjectableActivity;
import com.alcoholcountermeasuresystems.android.elan.fragments.dialogs.WarningDialogFragment;
import com.alcoholcountermeasuresystems.android.elan.managers.RealmStore;
import com.alcoholcountermeasuresystems.android.elan.views.HomeButtonLayout;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseInjectableActivity implements WarningDialogFragment.WarningDialogListener {

    @Inject
    RealmStore mRealmStore;

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarTitleText;

    @BindView(R.id.button_register)
    Button mRegisterButton;

    @BindView(R.id.layout_breath_test)
    HomeButtonLayout mBreathTestLayout;

    @OnClick(R.id.layout_breath_test)
    void onBreathTestPressed() {

    }

    @OnClick(R.id.button_register)
    void onRegisterButtonPressed() {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }

    @OnClick(R.id.layout_bac_estimation)
    void onBacEstimationPressed() {
        startActivity(new Intent(MainActivity.this, BacDisclaimerActivity.class));
    }

    @OnClick(R.id.layout_history)
    void onHistoryPressed() {
        startActivity(new Intent(MainActivity.this, HistoryActivity.class));
    }

    @OnClick(R.id.layout_information)
    void onInformationPressed() {
        startActivity(new Intent(MainActivity.this, InformationActivity.class));
    }

    @BindString(R.string.home_title)
    String mTitleString;

    @BindString(R.string.home_registered_button_title)
    String mRegisteredButtonString;

    @BindString(R.string.home_notregistered_button_title)
    String mNotRegisteredButtonString;

    @BindString(R.string.main_register_dialog_description)
    String mRegisterScanNearbyDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews(){
        initRegisterState(isLoggedin());
        initToolbar();
        if (!isLoggedin()){
            showRegisterDialog();
        }
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbarTitleText.setText(mTitleString);
    }

    private void initRegisterState(boolean isLoggedIn){
        mBreathTestLayout.setEnabled(isLoggedIn);
        if (isLoggedIn){
            mRegisterButton.setText(mRegisteredButtonString);
        }else {
            mRegisterButton.setText(mNotRegisteredButtonString);
        }
        mRegisterButton.setEnabled(!isLoggedIn);
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
            startActivity(new Intent(MainActivity.this, BacDisclaimerActivity.class));
            return true;
        }
        else if (id == R.id.action_contactus) {
            startActivity(new Intent(MainActivity.this, ContactUsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showRegisterDialog(){
        WarningDialogFragment dialogFragment = WarningDialogFragment.newInstance(mNotRegisteredButtonString,mRegisterScanNearbyDescription,false);
        dialogFragment.show(getSupportFragmentManager(), WarningDialogFragment.TAG);
    }

    @Override
    public void onDialogOkButtonClicked(WarningDialogFragment dialogFragment2) {
        // Todo insert code for nearby scan
        dialogFragment2.dismiss();
    }

    private boolean isLoggedin(){
        boolean loggedIn;
        if (mRealmStore.getProfile() == null){
            loggedIn = false;
        }else {
            loggedIn = true;
        }
        return loggedIn;
    }
}
