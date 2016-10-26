package com.alcoholcountermeasuresystems.android.elan;

import android.app.Application;

import com.alcoholcountermeasuresystems.android.elan.di.components.AppComponent;
import com.alcoholcountermeasuresystems.android.elan.di.components.DaggerAppComponent;
import com.alcoholcountermeasuresystems.android.elan.di.modules.AppModule;
import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.fabric.sdk.android.Fabric;
import net.danlew.android.joda.JodaTimeAndroid;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainApplication extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(RealmInspectorModulesProvider.builder(this)
                            .withLimit(1000)
                            .build())
                    .build());
        } else {
            Fabric.with(this, new Crashlytics());
        }

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setFontAttrId(R.attr.fontName)
                        .build()
        );

        JodaTimeAndroid.init(this);
        initAppComponent();
    }

    private void initAppComponent() {
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        sAppComponent.inject(this);
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

}
