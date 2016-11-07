package com.alcoholcountermeasuresystems.android.elan.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment2;
import android.view.Window;
import android.widget.NumberPicker;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.data.enums.Country;
import com.alcoholcountermeasuresystems.android.elan.fragments.base.BaseDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 07/11/16.
 */

public class CountryPickerDialog extends BaseDialogFragment {

    public static final String TAG = CountryPickerDialog.class.getCanonicalName();

    public interface CountryPickerDialogListener {
        void onCountryPicker(DialogFragment2 dialogFragment2, String countryName);
    }

    @BindView(R.id.picker_country)
    NumberPicker mCountryPicker;

    @OnClick(R.id.button_ok)
    void onOkClicked() {
        try{
            String countryname = Country.names()[mCountryPicker.getValue()];
            ((CountryPickerDialogListener) getActivity()).onCountryPicker(this,Country.names()[mCountryPicker.getValue()]);
        }catch (ClassCastException cce){
            throw new ClassCastException("ScanNearbyDialogListener getTargetFragment is not set");
        }

    }

    @OnClick(R.id.button_cancel)
    void onCancelClicked() {
        dismiss();
    }

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
