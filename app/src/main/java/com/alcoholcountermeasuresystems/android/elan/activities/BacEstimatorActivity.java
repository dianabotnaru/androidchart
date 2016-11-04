package com.alcoholcountermeasuresystems.android.elan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseActivity;
import com.alcoholcountermeasuresystems.android.elan.fragments.BacEstimatorOneFragment;
import com.alcoholcountermeasuresystems.android.elan.models.ProfileForBac;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 30/10/16.
 */

public class BacEstimatorActivity extends BaseActivity implements BacEstimatorOneFragment.BacEstimatorOneFragmentListener{
    @BindView(R.id.text_toolbar_title)
    TextView mToolbarTitleText;

    @BindView(R.id.button_toolbar_next)
    Button mToolbarNextButton;

    @BindString(R.string.bac_disclaimer_title)
    String mTitleString;

    @OnClick(R.id.button_toolbar_next)
    void onOkClicked() {
        //Todo save ProfileForbac in local
        startActivity(new Intent(BacEstimatorActivity.this, BacEstimationActivity.class));
    }

    private ProfileForBac profileForBac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac_estimator);
        ButterKnife.bind(this);
        profileForBac = new ProfileForBac();
        initViews();
    }

    private void initViews(){
        mToolbarTitleText.setText(mTitleString);
        mToolbarNextButton.setVisibility(View.VISIBLE);
        BacEstimatorOneFragment fragment = new BacEstimatorOneFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_bac_estimator1_content, fragment)
                .commit();
    }

    @Override
    public void onCompletedEstimatorSetting(boolean isCompleted, ProfileForBac profileForBac){
        mToolbarNextButton.setEnabled(isCompleted);
        if(profileForBac!= null){
            this.profileForBac = profileForBac;
        }
    }
}
