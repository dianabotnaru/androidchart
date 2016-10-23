package com.alcoholcountermeasuresystems.android.elan.fragments.base;

import android.support.v4.app.Fragment;

import butterknife.Unbinder;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment extends Fragment {

    protected Unbinder mUnbinder;
    protected CompositeSubscription mSubscriptions = new CompositeSubscription();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mSubscriptions.clear();
    }
}
