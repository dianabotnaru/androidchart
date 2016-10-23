package com.alcoholcountermeasuresystems.android.elan.di.components;

import android.content.Context;
import android.content.SharedPreferences;

import com.alcoholcountermeasuresystems.android.elan.activities.MainActivity;
import com.alcoholcountermeasuresystems.android.elan.MainApplication;
import com.alcoholcountermeasuresystems.android.elan.activities.SplashActivity;
import com.alcoholcountermeasuresystems.android.elan.di.AppScope;
import com.alcoholcountermeasuresystems.android.elan.di.modules.AppModule;
import com.alcoholcountermeasuresystems.android.elan.di.modules.RealmModule;
import com.alcoholcountermeasuresystems.android.elan.di.modules.SubscriptionManagerModule;

import dagger.Component;

@AppScope
@Component(modules = {
        AppModule.class,
        RealmModule.class,
        SubscriptionManagerModule.class
})
public interface AppComponent {
    void inject(MainApplication application);

    void inject(SplashActivity activity);

    void inject(MainActivity activity);


    MainApplication application();

    Context context();

    SharedPreferences sharedPreferences();
}
