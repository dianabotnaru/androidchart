package com.alcoholcountermeasuresystems.android.elan.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.fragments.base.BaseInjectableFragment;
import com.alcoholcountermeasuresystems.android.elan.models.Profile;
import com.alcoholcountermeasuresystems.android.elan.models.ProfileForBac;
import com.alcoholcountermeasuresystems.android.elan.utils.Internals;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jordi on 31/10/16.
 */

public class BacEstimatorOneFragment extends BaseInjectableFragment{

    public interface BacEstimatorOneFragmentListener {
        void onCompletedEstimatorSetting(boolean isCompleted,ProfileForBac profileForBac);
    }

    private static int AGE_MAX_VALUE = 100;
    private static int HELIGHT_FT_MAX_VALUE = 7;
    private static int HELIGHT_CENTIMETER_MAX_VALUE = 215;//7*30.48cm
    private static int HELIGHT_INCH_MAX_VALUE = 84;//7*12inch
    private static int WEIGHT_LBS_MAX_VALUE = 500;
    private static int WEIGHT_KG_MAX_VALUE = 220;

    @BindView(R.id.edittext_age)
    EditText mAgeEditText;

    @BindView(R.id.edittext_height)
    EditText mHeightEditText;

    @BindView(R.id.edittext_weight)
    EditText mWeightEditText;

    @BindView(R.id.checkBox_gender_female)
    CheckBox mFemaleCheckBox;

    @BindView(R.id.checkBox_gender_male)
    CheckBox mMaleCheckBox;

    @BindView(R.id.switch_in)
    Switch mInSwitch;

    @BindView(R.id.switch_lbs)
    Switch mlbsSwitch;

    @Override
    protected void injectComponents() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_bac_estimator_one, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews(){
        initAgeEditText();
        initHeightEditText();
        initWeightEditText();
        initCheckBoxs();
    }

    private void initAgeEditText(){
        mAgeEditText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.equals("")) {
                    if (Internals.getStringfromInt(mAgeEditText.getText().toString())>AGE_MAX_VALUE){
                        mAgeEditText.setText(Integer.toString(AGE_MAX_VALUE));
                    }
                    onCompletedEstimatorSetting();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void initHeightEditText(){
        mHeightEditText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                if(!s.equals("")) {
                    if (mInSwitch.isChecked()){
                        if (Internals.getStringfromInt(mHeightEditText.getText().toString())>HELIGHT_INCH_MAX_VALUE){
                            mHeightEditText.setText(Integer.toString(HELIGHT_INCH_MAX_VALUE));
                        }
                    }
                    else {
                        if (Internals.getStringfromInt(mHeightEditText.getText().toString())>HELIGHT_CENTIMETER_MAX_VALUE){
                            mHeightEditText.setText(Integer.toString(HELIGHT_CENTIMETER_MAX_VALUE));
                        }
                    }
                    onCompletedEstimatorSetting();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });
    }

    void initWeightEditText(){
        mWeightEditText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.equals("")) {
                    if(mlbsSwitch.isChecked()){
                        if (Internals.getStringfromInt(mWeightEditText.getText().toString())>WEIGHT_LBS_MAX_VALUE){
                            mWeightEditText.setText(Integer.toString(WEIGHT_LBS_MAX_VALUE));
                        }
                    }else{
                        if (Internals.getStringfromInt(mWeightEditText.getText().toString())>WEIGHT_KG_MAX_VALUE){
                            mWeightEditText.setText(Integer.toString(WEIGHT_KG_MAX_VALUE));
                        }
                    }
                    onCompletedEstimatorSetting();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });
    }

    void initCheckBoxs(){
        mMaleCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                     @Override
                                                     public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                         onCompletedEstimatorSetting();
                                                         mFemaleCheckBox.setChecked(!isChecked);

                                                     }
                                                 }
        );

        mFemaleCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                       @Override
                                                       public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                           onCompletedEstimatorSetting();
                                                           mMaleCheckBox.setChecked(!isChecked);
                                                       }
                                                   }
        );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void onCompletedEstimatorSetting(){
        try{
            ((BacEstimatorOneFragment.BacEstimatorOneFragmentListener) getActivity()).onCompletedEstimatorSetting(isCompletedEdit(),getProfileInformationforEstimator());
        }catch (ClassCastException cce){
            throw new ClassCastException("ScanNearbyDialogListener getTargetFragment is not set");
        }
    }

    private boolean isCompletedEdit() {
        return ((Internals.getStringfromInt(mAgeEditText.getText().toString())>0) &&
                (Internals.getStringfromInt(mHeightEditText.getText().toString())>0) &&
                (Internals.getStringfromInt(mWeightEditText.getText().toString())>0))&&
                (mFemaleCheckBox.isChecked()||mMaleCheckBox.isChecked());
    }

    private ProfileForBac getProfileInformationforEstimator(){
        if(isCompletedEdit()){
            ProfileForBac profileForBac = new ProfileForBac();
            profileForBac.setAge(Integer.valueOf(mAgeEditText.getText().toString()));
            profileForBac.setHeight(Integer.valueOf(mHeightEditText.getText().toString()));
            profileForBac.setWeight(Integer.valueOf(mWeightEditText.getText().toString()));
            if (mMaleCheckBox.isChecked()){
                profileForBac.setGender("M");
            }else {
                profileForBac.setGender("F");
            }
            if (mInSwitch.isChecked()){
                profileForBac.setHeightMetric("cm");
            }else {
                profileForBac.setHeightMetric("in");
            }
            if (mlbsSwitch.isChecked()){
                profileForBac.setHeightMetric("lb");
            }else {
                profileForBac.setHeightMetric("kg");
            }
            return profileForBac;
        }
        else {
            return null;
        }
    }
}
