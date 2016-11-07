package com.alcoholcountermeasuresystems.android.elan.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.widget.NumberPicker;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.data.enums.Country;
import com.alcoholcountermeasuresystems.android.elan.fragments.base.BaseDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jordi on 07/11/16.
 */

public class CountryPickerDialog extends BaseDialogFragment {

    public static final String TAG = CountryPickerDialog.class.getCanonicalName();

    @BindView(R.id.picker_country)
    NumberPicker mCountryPicker;

    public static CountryPickerDialog newInstance() {
        return new CountryPickerDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_country_picker_dialog);
        mUnbinder = ButterKnife.bind(this, dialog.getWindow().getDecorView());
        initCountryPicker();
        return dialog;
    }

    private void initCountryPicker(){
        mCountryPicker.setMinValue(0);
        mCountryPicker.setMaxValue(Country.names().length - 1);
        mCountryPicker.setDisplayedValues(Country.names());
    }
}
