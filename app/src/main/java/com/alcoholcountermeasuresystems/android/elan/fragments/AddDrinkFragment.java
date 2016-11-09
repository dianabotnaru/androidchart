package com.alcoholcountermeasuresystems.android.elan.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import static com.alcoholcountermeasuresystems.android.elan.data.enums.BundleKey.KeyBac;
import static com.alcoholcountermeasuresystems.android.elan.data.enums.BundleKey.KeyIsComeHistory;
import static com.alcoholcountermeasuresystems.android.elan.data.enums.MeasurementUnit.FluidOunce;
import static com.alcoholcountermeasuresystems.android.elan.data.enums.MeasurementUnit.Milliter;

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

    @BindView(R.id.button_edit_drink)
    Button mEditDrinkButton;

    @BindView(R.id.button_delete_entry)
    Button mDeleteEntryButton;

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

    @BindString(R.string.add_drink_save_drink_button)
    String mSaveDrinkString;

    public BAC mBac;

    public boolean mIsComeFromHistory = false;

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

    public static AddDrinkFragment newInstance(BAC bac,boolean isComeFromHistory) {
        AddDrinkFragment addDrinkFragment = new AddDrinkFragment();
        Bundle args = new Bundle();
        args.putBoolean(KeyIsComeHistory.toString(), isComeFromHistory);
        if (isComeFromHistory) {
            args.putParcelable(KeyBac.toString(), bac);
        }
        addDrinkFragment.setArguments(args);
        return addDrinkFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsComeFromHistory = getArguments().getBoolean(KeyIsComeHistory.toString());
        if (mIsComeFromHistory){
            mBac = getArguments().getParcelable(KeyBac.toString());
        }
    }


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
        if(mIsComeFromHistory){
            initViewsForHistory(mBac);
        }
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
            bac.setConsumptionMetric(Milliter.toString());
        }else {
            bac.setConsumptionMetric(FluidOunce.toString());
        }
        if (mNowCheckBox.isChecked()){
            DateTime nowTime = new DateTime();
            bac.setTimestamp(nowTime.getMillis() / 1000);
        }else {
            bac.setTimestamp(selectedDate.getMillis() / 1000);
        }
        return bac;
    }

    private void initViewsForHistory(BAC bac){
        mVolumeConsumedEditText.setText(String.valueOf(bac.getVolumeConsumption()));
        mPercentageEditText.setText(String.valueOf(bac.getPercentageConsumption()));
        if (bac.getConsumptionMetric().equals(Milliter.toString())){
            mConsumedMatricSwitch.setChecked(true);
        }else {
            mConsumedMatricSwitch.setChecked(false);
        }
        DateTime dateTime = new DateTime(bac.getTimeStamp()*1000);
        mSelectDateTimeText.setText(DateUtils.getStringFromdate(dateTime.toDate()));

        mEditDrinkButton.setVisibility(View.VISIBLE);
        mDeleteEntryButton.setVisibility(View.VISIBLE);
        mAddDrinkButton.setText(mSaveDrinkString);
    }
}
