package com.alcoholcountermeasuresystems.android.elan.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.DialogFragment2;
import android.view.Window;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.fragments.base.BaseDialogFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 28/10/16.
 */

public class ScanNearbyDialogFragment extends BaseDialogFragment {

    public interface ScanNearbyDialogListener {
        void onScanNearbyClicked(DialogFragment2 dialogFragment2);
    }

    @OnClick(R.id.button_ok)
    void onOkClicked() {
        try{
            ((ScanNearbyDialogListener) getActivity()).onScanNearbyClicked(this);
        }catch (ClassCastException cce){
            throw new ClassCastException("ScanNearbyDialogListener getTargetFragment is not set");
        }
    }

    @OnClick(R.id.button_cancel)
    void onCancelClicked() {
        dismiss();
    }

    public static final String TAG = ScanNearbyDialogFragment.class.getCanonicalName();

    public ScanNearbyDialogFragment() {
    }

    public static ScanNearbyDialogFragment newInstance() {
        return new ScanNearbyDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
    }

}
