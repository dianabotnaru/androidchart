package com.alcoholcountermeasuresystems.android.elan.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alcoholcountermeasuresystems.android.elan.MainApplication;
import com.alcoholcountermeasuresystems.android.elan.di.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final MainApplication mApplication;
    private final SharedPreferences mSharedPrefs;

    public AppModule(MainApplication application) {
        mApplication = application;
        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(mApplication);
    }

    @AppScope
    @Provides
    MainApplication provideApplication() {
        return mApplication;
    }

    @AppScope
    @Provides
    Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }

    @AppScope
    @Provides
    SharedPreferences provideSharedPreferences() {
        return mSharedPrefs;
    }
}
