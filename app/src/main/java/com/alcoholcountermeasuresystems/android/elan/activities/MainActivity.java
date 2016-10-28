package com.alcoholcountermeasuresystems.android.elan.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.MainApplication;
import com.alcoholcountermeasuresystems.android.elan.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarTitleText;

    @BindView(R.id.button_register)
    Button mRegisterButton;

    @BindView(R.id.layout_breath_test)
    RelativeLayout mBreathTestLayout;

    @OnClick(R.id.button_action)
    void onToolbarActionPressed() {
    }

    @OnClick(R.id.button_register)
    void onRegisterButtonPressed() {
        notRegistered=(!notRegistered);
        initRegisterState(notRegistered);
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
        mToolbarTitleText.setText(mTitleString);
        initRegisterState(true);
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

}
