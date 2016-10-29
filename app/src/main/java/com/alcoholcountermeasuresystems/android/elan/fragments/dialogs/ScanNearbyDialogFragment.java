package com.alcoholcountermeasuresystems.android.elan.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.fragments.base.BaseDialogFragment;

import butterknife.ButterKnife;

/**
 * Created by jordi on 28/10/16.
 */

public class ScanNearbyDialogFragment extends BaseDialogFragment {

    public static final String TAG = ScanNearbyDialogFragment.class.getCanonicalName();

    public ScanNearbyDialogFragment() {
    }

    public static ScanNearbyDialogFragment newInstance() {
        return new ScanNearbyDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_scannearby);
        mUnbinder = ButterKnife.bind(this, dialog.getWindow().getDecorView());
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}
