package com.alcoholcountermeasuresystems.android.elan.di.components;

import android.content.Context;
import android.content.SharedPreferences;

import com.alcoholcountermeasuresystems.android.elan.MainApplication;
import com.alcoholcountermeasuresystems.android.elan.activities.AddDrinkActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.RegisterActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.main.SplashActivity;
import com.alcoholcountermeasuresystems.android.elan.activities.main.MainActivity;
import com.alcoholcountermeasuresystems.android.elan.di.AppScope;
import com.alcoholcountermeasuresystems.android.elan.di.modules.AppModule;
import com.alcoholcountermeasuresystems.android.elan.di.modules.RealmModule;
import com.alcoholcountermeasuresystems.android.elan.di.modules.SubscriptionManagerModule;
import com.alcoholcountermeasuresystems.android.elan.managers.RealmStore;

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
    void inject(RegisterActivity activity);
    void inject(AddDrinkActivity activity);

    MainApplication application();

    Context context();
    SharedPreferences sharedPreferences();
    RealmStore realmStore();
}
