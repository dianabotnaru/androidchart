package android.support.v4.app;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class DialogFragment2 extends DialogFragment {

    public int showAllowingStateLoss(FragmentTransaction transaction, String tag) {
        mDismissed = false;
        mShownByMe = true;
        mViewDestroyed = false;

        transaction.add(this, tag);
        mBackStackId = transaction.commitAllowingStateLoss();

        return mBackStackId;
    }

    public void showAllowingStateLoss(FragmentManager manager, String tag) {
        mDismissed = false;
        mShownByMe = true;

        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }
}
