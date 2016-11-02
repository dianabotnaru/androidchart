package com.alcoholcountermeasuresystems.android.elan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment2;
import android.support.v4.app.FragmentTransaction;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseInjectableActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.main.MainActivity;
import com.alcoholcountermeasuresystems.android.elan.fragments.RegisterFragment;
import com.alcoholcountermeasuresystems.android.elan.fragments.dialogs.ScanNearbyDialogFragment;
import com.alcoholcountermeasuresystems.android.elan.models.Profile;
import com.alcoholcountermeasuresystems.android.elan.views.ProfileInputLayout;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 26/10/16.
 */

public class RegisterActivity extends BaseInjectableActivity implements ScanNearbyDialogFragment.ScanNearbyDialogListener,RegisterFragment.RegisterFragmentListener {


    @OnClick(R.id.button_cancel)
    void onToolbarBackPressed() {
        onBackPressed();
    }

    private Profile mProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initViews();
        showScanNearbyDialog();
    }

    @Override
    protected void injectComponents() {
    }

    @Override
    public void onScanNearbyClicked(DialogFragment2 dialogFragment2) {
        // Todo insert code for nearby scan
        dialogFragment2.dismiss();
    }

    private void initViews(){
        RegisterFragment fragment = new RegisterFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_register_content, fragment)
                .commit();
    }

    private void showScanNearbyDialog(){
        ScanNearbyDialogFragment dialogFragment = ScanNearbyDialogFragment.newInstance();
        dialogFragment.show(getSupportFragmentManager(), ScanNearbyDialogFragment.TAG);
    }

    @Override
    public void onActivate(Profile profile) {
        //Todo  integrateion backend API for register

        profile.save(); //save profile information in local
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }
}
