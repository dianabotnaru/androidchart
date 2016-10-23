package com.alcoholcountermeasuresystems.android.elan.fragments.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import butterknife.Unbinder;
import timber.log.Timber;

public abstract class BaseInjectableFragment extends BaseFragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Timber.d("Calling onAttach");
        injectComponents();
    }

    protected abstract void injectComponents();
}
