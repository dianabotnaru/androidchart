package com.alcoholcountermeasuresystems.android.elan.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.fragments.base.BaseInjectableFragment;
import com.alcoholcountermeasuresystems.android.elan.views.ProfileInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 02/11/16.
 */

public class RegisterFragment extends BaseInjectableFragment {


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

    @OnClick(R.id.button_register_activate)
    void onRegisterActivate() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews(){
        mCountryLayout.setDropDownButtonPressedListener(new ProfileInputLayout.OnDropDownButtonPressedListener() {
            public void onDropDownButtonClicked(){
                Toast.makeText(getContext(), "country dropdown button clicked", Toast.LENGTH_SHORT).show();
                //do whatever you want to do when the event is performed.
            }
        });
        mLanguageLayout.setDropDownButtonPressedListener(new ProfileInputLayout.OnDropDownButtonPressedListener() {
            public void onDropDownButtonClicked(){
                Toast.makeText(getContext(), "Language dropdown button clicked", Toast.LENGTH_SHORT).show();
                //do whatever you want to do when the event is performed.
            }
        });
    }

    @Override
    protected void injectComponents() {}
}
