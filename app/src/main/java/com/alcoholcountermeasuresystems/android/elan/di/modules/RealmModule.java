package com.alcoholcountermeasuresystems.android.elan.di.modules;

import android.content.Context;

import com.alcoholcountermeasuresystems.android.elan.di.AppScope;
import com.alcoholcountermeasuresystems.android.elan.managers.RealmStore;

import dagger.Module;
import dagger.Provides;

@Module
public class RealmModule {
    private RealmStore mRealmStore;

    @AppScope
    @Provides
    RealmStore provideRealmStore(Context context) {
        mRealmStore = new RealmStore.Builder(context).build();
        RealmStore.setSingletonInstance(mRealmStore);
        return mRealmStore;
    }
}
