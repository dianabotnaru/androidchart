package com.alcoholcountermeasuresystems.android.elan.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.fragments.base.BaseInjectableFragment;
import com.alcoholcountermeasuresystems.android.elan.fragments.dialogs.DateTimePickerFragment;
import com.alcoholcountermeasuresystems.android.elan.models.BAC;
import com.alcoholcountermeasuresystems.android.elan.utils.DateUtils;
import com.alcoholcountermeasuresystems.android.elan.utils.Internals;

import org.joda.time.DateTime;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 31/10/16.
 */

public class AddDrinkFragment extends BaseInjectableFragment{

    public interface AddDrinkFragmentListener {
        void onAddDrink(BAC bac);
    }

    @BindView(R.id.text_description)
    TextView mDescriptionText;

    @BindView(R.id.text_select_date_time)
    TextView mSelectDateTimeText;

    @BindView(R.id.edittext_volume_consumed)
    EditText mVolumeConsumedEditText;

    @BindView(R.id.edittext_percentage)
    EditText mPercentageEditText;

    @BindView(R.id.button_add_drink)
    Button mAddDrinkButton;

    @BindView(R.id.layout_select_date_time)
    RelativeLayout mSelectDateTimeLayout;

    @BindView(R.id.checkBox_now)
    CheckBox mNowCheckBox;

    @BindView(R.id.switch_consumed_matric)
    Switch mConsumedMatricSwitch;

    @BindString(R.string.add_drink_description)
    String mDisableDescriptionString;

    @BindString(R.string.add_drink_enable_description)
    String mEnableDescriptionString;

    @OnClick(R.id.layout_select_date_time)
    void onOkClicked() {
        DateTimePickerFragment dialogFragment = DateTimePickerFragment.newInstance();
        dialogFragment.show(getFragmentManager(), DateTimePickerFragment.TAG);
    }

    @OnClick(R.id.button_add_drink)
    void onAddDrinkButtonPressed() {
        try{
            ((AddDrinkFragment.AddDrinkFragmentListener) getActivity()).onAddDrink(setBacModel());
        }catch (ClassCastException cce){
            throw new ClassCastException("ScanNearbyDialogListener getTargetFragment is not set");
        }
    }

    private DateTime selectedDate;

    @Override
    protected void injectComponents() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_drink, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void initViews(){
        initNowCheckBox();
        initEditText();
    }

    private void initNowCheckBox(){
        mNowCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                     @Override
                                                     public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                         if (isChecked){
                                                             mSelectDateTimeLayout.setVisibility(View.GONE);
                                                         }else{
                                                             mSelectDateTimeLayout.setVisibility(View.VISIBLE);
                                                         }
                                                         enableAddDrink();
                                                     }
                                                 }
        );
    }

    public void setDateTimeTextview(DateTime dateTime){
        selectedDate = dateTime;
        mSelectDateTimeText.setText(DateUtils.getStringFromdate(dateTime.toDate()));
        enableAddDrink();
    }

    private void enableAddDrink(){
        mAddDrinkButton.setEnabled(isCompletedEdit());
        if (isCompletedEdit()){
            mDescriptionText.setText(mEnableDescriptionString);
        }else{
            mDescriptionText.setText(mDisableDescriptionString);
        }
    }

    private boolean isCompletedEdit() {
        return ((Internals.getDoublefromString(mVolumeConsumedEditText.getText().toString())>0) &&
                (Internals.getDoublefromString(mPercentageEditText.getText().toString())>0) &&
                (mNowCheckBox.isChecked()||(selectedDate != null)));
    }

    private void initEditText(){
        mVolumeConsumedEditText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.equals("")) {
                    enableAddDrink();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });

        mPercentageEditText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.equals("")) {
                    enableAddDrink();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private BAC setBacModel(){
        BAC bac = new BAC();
        bac.setVolumeConsumption(Internals.getDoublefromString(mVolumeConsumedEditText.getText().toString()));
        bac.setPercentageConsumption(Internals.getDoublefromString(mPercentageEditText.getText().toString()));
        if (mConsumedMatricSwitch.isChecked()){
            bac.setConsumptionMetric("ml");
        }else {
            bac.setConsumptionMetric("oz");
        }
        if (mNowCheckBox.isChecked()){
            DateTime nowTime = new DateTime();
            bac.setTimestamp(nowTime.getMillis() / 1000);
        }else {
            bac.setTimestamp(selectedDate.getMillis() / 1000);
        }
        return bac;
    }
}
