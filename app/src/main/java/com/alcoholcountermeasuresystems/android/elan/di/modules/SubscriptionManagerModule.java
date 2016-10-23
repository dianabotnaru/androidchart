package com.alcoholcountermeasuresystems.android.elan.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.alcoholcountermeasuresystems.android.elan.di.AppScope;
import com.alcoholcountermeasuresystems.android.elan.managers.RealmStore;
import com.alcoholcountermeasuresystems.android.elan.managers.SubscriptionManager;

import dagger.Module;
import dagger.Provides;

@Module
public class SubscriptionManagerModule {
    private SubscriptionManager mSubscriptionManager;

    @AppScope
    @Provides
    SubscriptionManager provideSubscriptionManager(Context context, RealmStore realmStore, SharedPreferences sharedPreferences) {
        mSubscriptionManager = new SubscriptionManager.Builder(context)
                .realmStore(realmStore)
                .sharedPreferences(sharedPreferences)
                .build();
        SubscriptionManager.setSingletonInstance(mSubscriptionManager);
        return mSubscriptionManager;
    }
}
