package com.alcoholcountermeasuresystems.android.elan.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.fragments.base.BaseInjectableFragment;
import com.alcoholcountermeasuresystems.android.elan.fragments.dialogs.DateTimePickerFragment;
import com.alcoholcountermeasuresystems.android.elan.utils.DateUtils;


import org.joda.time.LocalDateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 31/10/16.
 */

public class AddDrinkFragment extends BaseInjectableFragment{

    @BindView(R.id.text_description)
    TextView mDescriptionText;

    @BindView(R.id.text_select_date_time)
    TextView mSelectDateTimeText;

    @BindView(R.id.button_add_drink)
    Button mAddDrinkButton;

    @BindView(R.id.layout_select_date_time)
    RelativeLayout mSelectDateTimeLayout;

    @BindView(R.id.checkBox_now)
    CheckBox mNowCheckBox;

    @BindString(R.string.add_drink_description)
    String mDisableDescriptionString;

    @BindString(R.string.add_drink_enable_description)
    String mEnableDescriptionString;

    @OnClick(R.id.layout_select_date_time)
    void onOkClicked() {
        DateTimePickerFragment dialogFragment = DateTimePickerFragment.newInstance();
        dialogFragment.show(getFragmentManager(), DateTimePickerFragment.TAG);
    }

    private Date selectedDate;

    @Override
    protected void injectComponents() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_drink, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initNowCheckBox();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void initNowCheckBox(){
        mNowCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                     @Override
                                                     public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                         if (isChecked){
                                                             enableAddDrink(true);
                                                             mSelectDateTimeLayout.setVisibility(View.GONE);
                                                         }else{
                                                             if (selectedDate == null){
                                                                 enableAddDrink(false);
                                                             }
                                                             mSelectDateTimeLayout.setVisibility(View.VISIBLE);
                                                         }
                                                     }
                                                 }
        );
    }

    public void setDateTimeTextview(LocalDateTime localDateTime){
        selectedDate = localDateTime.toDate();
        mAddDrinkButton.setEnabled(true);
        mSelectDateTimeText.setText(DateUtils.getStringFromdate(selectedDate));
    }

    private void enableAddDrink(boolean isEnabled){
        mAddDrinkButton.setEnabled(isEnabled);
        if (isEnabled){
            mDescriptionText.setText(mEnableDescriptionString);
        }else{
            mDescriptionText.setText(mDisableDescriptionString);
        }
    }
}
