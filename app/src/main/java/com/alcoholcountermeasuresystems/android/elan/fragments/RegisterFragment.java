package com.alcoholcountermeasuresystems.android.elan.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.fragments.base.BaseInjectableFragment;
import com.alcoholcountermeasuresystems.android.elan.fragments.dialogs.CountryPickerDialog;
import com.alcoholcountermeasuresystems.android.elan.models.Profile;
import com.alcoholcountermeasuresystems.android.elan.utils.RegexUtils;
import com.alcoholcountermeasuresystems.android.elan.views.ProfileInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 02/11/16.
 */

public class RegisterFragment extends BaseInjectableFragment {

    public interface RegisterFragmentListener {
        void onActivate(Profile profile);
    }

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

    @BindView(R.id.button_register_activate)
    Button mActivateButton;

    @OnClick(R.id.button_register_activate)
    void onRegisterActivate() {
        try{
            ((RegisterFragment.RegisterFragmentListener) getActivity()).onActivate(setProfile());
        }catch (ClassCastException cce){
            throw new ClassCastException("ScanNearbyDialogListener getTargetFragment is not set");
        }
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
                //Todo insert code to show country list menu and remove below line
                CountryPickerDialog dialogFragment = CountryPickerDialog.newInstance();
                dialogFragment.show(getFragmentManager(), CountryPickerDialog.TAG);

                Toast.makeText(getContext(), "country dropdown button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        mLanguageLayout.setDropDownButtonPressedListener(new ProfileInputLayout.OnDropDownButtonPressedListener() {
            public void onDropDownButtonClicked(){
                //Todo insert code to show language list menu and remove below line
                Toast.makeText(getContext(), "Language dropdown button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        initProfileInputLayoutTextChangedListner(mFirstNameLayout);
        initProfileInputLayoutTextChangedListner(mLastNameLayout);
        initProfileInputLayoutTextChangedListner(mEmailLayout);
        initProfileInputLayoutTextChangedListner(mLanguageLayout);
        initProfileInputLayoutTextChangedListner(mCountryLayout);
        initProfileInputLayoutTextChangedListner(mSerialLayout);
        initProfileInputLayoutTextChangedListner(mPurchasedLayout);
        initProfileInputLayoutTextChangedListner(mLocationLayout);
        setSerialNumber();
    }

    void initProfileInputLayoutTextChangedListner(ProfileInputLayout inputLayout){
        inputLayout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mActivateButton.setEnabled(isCompletedEdit());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private Boolean isCompletedEdit(){
        return ((!mFirstNameLayout.getInputText().equals(""))&&
                (!mLastNameLayout.getInputText().equals(""))&&
                (!mEmailLayout.getInputText().equals(""))&&
                (RegexUtils.validateEmail(mEmailLayout.getInputText()))&& // check email validation
//                (!mLanguageLayout.getInputText().equals(""))&&
//                (!mCountryLayout.getInputText().equals(""))&&
                (!mSerialLayout.getInputText().equals(""))&&
                (!mPurchasedLayout.getInputText().equals(""))&&
                (!mLocationLayout.getInputText().equals("")));
    }

    private Profile setProfile(){
        Profile mProfile = new Profile();
        mProfile.setFirstName(mFirstNameLayout.getInputText());
        mProfile.setLastName(mLastNameLayout.getInputText());
        mProfile.setEmail(mEmailLayout.getInputText());
        mProfile.setLanguage(mLanguageLayout.getInputText());
        mProfile.setCountry(mCountryLayout.getInputText());
        mProfile.setSerialNumber(mSerialLayout.getInputText());
        mProfile.setPurchased(mPurchasedLayout.getInputText());
        mProfile.setLocation(mLocationLayout.getInputText());
        return mProfile;
    }

    private void setSerialNumber(){
        // Todo insert code to get serial number
        // Todo remove below line This is for QA.
        String serialNumber = "1111-1111-1111-1111";
        mSerialLayout.setInputText(serialNumber);
    }

    @Override
    protected void injectComponents() {}
}
