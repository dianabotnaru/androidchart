package com.alcoholcountermeasuresystems.android.elan.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment2;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.MainApplication;
import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.fragments.base.BaseInjectableFragment;
import com.alcoholcountermeasuresystems.android.elan.fragments.dialogs.ScanNearbyDialogFragment;
import com.alcoholcountermeasuresystems.android.elan.models.Profile;
import com.alcoholcountermeasuresystems.android.elan.utils.Internals;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jordi on 31/10/16.
 */

public class BacEstimatorOneFragment extends BaseInjectableFragment{

    public interface BacEstimatorOneFragmentListener {
        void onCompletedEstimatorSetting(boolean isCompleted);
    }

    private Profile profile; //QA purpose

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
        initEditViews();
        initCheckBoxs();
    }

    void initEditViews(){
        mAgeEditText.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(!s.equals("")) {
                    onCompletedEstimatorSetting();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        mHeightEditText.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(!s.equals("")) {
                    onCompletedEstimatorSetting();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });
        mWeightEditText.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(!s.equals("")) {
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
            ((BacEstimatorOneFragment.BacEstimatorOneFragmentListener) getActivity()).onCompletedEstimatorSetting(isCompletedEdit());
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

}
