package com.alcoholcountermeasuresystems.android.elan.fragments.base;

import android.support.v4.app.DialogFragment2;

import butterknife.Unbinder;

public abstract class BaseDialogFragment extends DialogFragment2 {

    protected Unbinder mUnbinder;

    public BaseDialogFragment() {
        //required empty constructor
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
