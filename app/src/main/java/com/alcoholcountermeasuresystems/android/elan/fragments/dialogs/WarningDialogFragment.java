package com.alcoholcountermeasuresystems.android.elan.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.DialogFragment2;
import android.view.Window;
import android.widget.TextView;

import com.alcoholcountermeasuresystems.android.elan.R;
import com.alcoholcountermeasuresystems.android.elan.fragments.base.BaseDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jordi on 28/10/16.
 */

public class WarningDialogFragment extends BaseDialogFragment {

    private static String KEY_TITLE_STRING = "title";
    private static String KEY_DESCRIPTION_STRING = "description";

    private String mTitleString;
    private String mDescriptionString;

    @BindView(R.id.text_dialog_title)
    TextView mDialogTitleText;

    @BindView(R.id.text_dialog_description)
    TextView mDialogDescriptionText;

    public interface WarningDialogListener {
        void onDialogOkButtonClicked(DialogFragment2 dialogFragment2);
    }

    @OnClick(R.id.button_ok)
    void onOkClicked() {
        try{
            ((WarningDialogListener) getActivity()).onDialogOkButtonClicked(this);
        }catch (ClassCastException cce){
            throw new ClassCastException("ScanNearbyDialogListener getTargetFragment is not set");
        }
    }

    @OnClick(R.id.button_cancel)
    void onCancelClicked() {
        dismiss();
    }

    public static final String TAG = WarningDialogFragment.class.getCanonicalName();

    public WarningDialogFragment() {
    }

    public static WarningDialogFragment newInstance(String title,String description) {
        Bundle arguments = new Bundle();
        arguments.putString(KEY_TITLE_STRING, title);
        arguments.putString(KEY_DESCRIPTION_STRING, description);

        WarningDialogFragment dialog = new WarningDialogFragment();
        dialog.setArguments(arguments);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mTitleString = arguments.getString(KEY_TITLE_STRING);
        mDescriptionString = arguments.getString(KEY_DESCRIPTION_STRING);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_dialog_warning);
        mUnbinder = ButterKnife.bind(this, dialog.getWindow().getDecorView());
        initViews();
        return dialog;
    }

    private void initViews(){
        mDialogTitleText.setText(mTitleString);
        mDialogDescriptionText.setText(mDescriptionString);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
