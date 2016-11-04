package com.alcoholcountermeasuresystems.android.elan.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.fragments.base.BaseDialogFragment;

import butterknife.ButterKnife;

/**
 * Created by jordi on 04/11/16.
 */

public class DateTimePickerFragment extends BaseDialogFragment {

    public static final String TAG = DateTimePickerFragment.class.getCanonicalName();

    public static DateTimePickerFragment newInstance() {
        return new DateTimePickerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_date_time_picker_dialog);
        mUnbinder = ButterKnife.bind(this, dialog.getWindow().getDecorView());
        return dialog;
    }

}
