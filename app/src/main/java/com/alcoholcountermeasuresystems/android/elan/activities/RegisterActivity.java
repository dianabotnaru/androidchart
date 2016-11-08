package com.alcoholcountermeasuresystems.android.elan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment2;
import android.support.v4.app.FragmentTransaction;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.alcoholcountermeasuresystems.android.elan.MainApplication;
import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.activities.base.BaseInjectableActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.main.MainActivity;
import com.alcoholcountermeasuresystems.android.elan.fragments.RegisterFragment;
import com.alcoholcountermeasuresystems.android.elan.fragments.dialogs.CountryPickerDialog;
import com.alcoholcountermeasuresystems.android.elan.fragments.dialogs.WarningDialogFragment;
import com.alcoholcountermeasuresystems.android.elan.managers.RealmStore;
import com.alcoholcountermeasuresystems.android.elan.models.Profile;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 26/10/16.
 */

public class RegisterActivity extends BaseInjectableActivity implements WarningDialogFragment.WarningDialogListener,RegisterFragment.RegisterFragmentListener,CountryPickerDialog.CountryPickerDialogListener {

    @Inject
    RealmStore mRealmStore;

    @OnClick(R.id.button_cancel)
    void onToolbarBackPressed() {
        onBackPressed();
    }

    @BindString(R.string.register_scan_dialog_title)
    String mRegisterScanNearbyTitle;

    @BindString(R.string.register_scan_dialog_description)
    String mRegisterScanNearbyDescription;

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
        MainApplication.getAppComponent().inject(this);
    }

    @Override
    public void onDialogOkButtonClicked(DialogFragment2 dialogFragment2) {
        // Todo insert code for Elan scan
        dialogFragment2.dismiss();
    }

    private void initViews(){
        RegisterFragment fragment = new RegisterFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_register_content, fragment)
                .commit();
    }

    private void showScanNearbyDialog(){
        WarningDialogFragment dialogFragment = WarningDialogFragment.newInstance(mRegisterScanNearbyTitle,mRegisterScanNearbyDescription,false);
        dialogFragment.show(getSupportFragmentManager(), WarningDialogFragment.TAG);
    }

    @Override
    public void onActivate(Profile profile) {
        mRealmStore.addProfile(profile);
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onCountryPicker(DialogFragment2 dialogFragment2, String countryName) {
        RegisterFragment registerFragment = (RegisterFragment)
                getSupportFragmentManager().findFragmentById(R.id.layout_register_content);
        if (registerFragment != null) {
            registerFragment.setCountryName(countryName);
        }
        dialogFragment2.dismiss();
    }
}
