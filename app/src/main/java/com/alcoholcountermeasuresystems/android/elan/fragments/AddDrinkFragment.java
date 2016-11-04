package com.alcoholcountermeasuresystems.android.elan.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.fragments.base.BaseInjectableFragment;
import com.alcoholcountermeasuresystems.android.elan.fragments.dialogs.DateTimePickerFragment;


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

    @BindString(R.string.add_drink_description)
    String mDisableDescriptionString;

    @BindString(R.string.add_drink_enable_description)
    String mEnableDescriptionString;

    @OnClick(R.id.layout_select_date_time)
    void onOkClicked() {
        DateTimePickerFragment dialogFragment = DateTimePickerFragment.newInstance();
        dialogFragment.show(getFragmentManager(), DateTimePickerFragment.TAG);
    }

    @Override
    protected void injectComponents() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_drink, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void setDateTimeTextview(Date date){
        String dateString = new SimpleDateFormat("EE").format(date)+", "+ DateFormat.getDateInstance().format(date);
        String timeString = DateFormat.getTimeInstance().format(date);
        mSelectDateTimeText.setText(dateString+" "+timeString);
    }
}
