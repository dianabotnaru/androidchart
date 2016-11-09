package com.alcoholcountermeasuresystems.android.elan.managers;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alcoholcountermeasuresystems.android.elan.models.BAC;
import com.alcoholcountermeasuresystems.android.elan.models.Profile;
import com.alcoholcountermeasuresystems.android.elan.utils.Internals;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmStore {

    private final Context mContext;
    private final String mDatabaseName;
    static volatile RealmStore singleton = null;

    /***
     * Method to get the Datastore instance
     *
     * @param context Activity or Application Context
     * @return A static instance of Datastore
     */
    public static RealmStore with(Context context) {
        if (singleton == null) {
            synchronized (RealmStore.class) {
                if (singleton == null) {
                    singleton = new Builder(context).build();
                }
            }
        }
        return singleton;
    }

    /***
     * Package-protected constructor - Called from the Builder
     *
     * @param context      Activity or Application context
     * @param databaseName The name for the local Database
     */
    RealmStore(@NonNull Context context,
               @NonNull String databaseName,
               @NonNull boolean deleteOnInit) {
        // Grab the application context in case an activity context was passed in
        mContext = context.getApplicationContext();
        mDatabaseName = databaseName;

        Realm.init(mContext);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(mDatabaseName + ".realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();

        if (deleteOnInit) {
            Realm.deleteRealm(config);
        }
        Realm.setDefaultConfiguration(config);
    }

    public void doSomething() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.commitTransaction();
    }


    /**
     * Set the global instance returned from {@link #with}.
     * <p>
     * This method must be called before any calls to {@link #with} and may only be called once.
     */
    public static void setSingletonInstance(RealmStore realmStore) {
        if (realmStore == null) {
            throw new IllegalArgumentException("RealmStore must not be null.");
        }
        synchronized (RealmStore.class) {
            if (singleton != null) {
                throw new IllegalStateException("Singleton instance already exists.");
            }
            singleton = realmStore;
        }
    }
    public void addProfile(@NonNull Profile profile) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(profile);
        realm.commitTransaction();
    }

    public Profile getProfile(){
        Realm realm = Realm.getDefaultInstance();
        Profile savedProfile = realm.where(Profile.class).findFirst();
        return savedProfile;
    }

    public void addBac(@NonNull BAC bac) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        BAC cachedBac = realm.createObject(BAC.class);
        cachedBac.setTimestamp(bac.getTimeStamp());
        cachedBac.setVolumeConsumption(bac.getVolumeConsumption());
        cachedBac.setPercentageConsumption(bac.getPercentageConsumption());
        realm.commitTransaction();
    }

    public List<BAC> retrieveBacs(@NonNull DateTime day) {
        Realm realm = Realm.getDefaultInstance();

        long startTimestamp =  (day.minusHours(12).getMillis() / 1000);
        long endTimestamp =  (day.plusHours(12).getMillis() / 1000);
        RealmResults<BAC> results = realm.where(BAC.class)
                .between("timestamp", startTimestamp, endTimestamp)
                .findAll();

        return mapToBacs(results);
    }

    public List<BAC> retrieveBacsforDay(@NonNull DateTime day) {

        Realm realm = Realm.getDefaultInstance();
        long startTimestamp =  (day.withTimeAtStartOfDay().getMillis() / 1000);
        long endTimestamp =  (day.withTimeAtStartOfDay().plusHours(24).getMillis() / 1000);
        RealmResults<BAC> results = realm.where(BAC.class)
                .between("timestamp", startTimestamp, endTimestamp)
                .findAll();
        return mapToBacs(results);
    }

    private List<BAC> mapToBacs(RealmResults<BAC> realmResults) {
        List<BAC> bacs = new ArrayList<>(realmResults.size());
        for (BAC bac : realmResults) {
            BAC newBac = new BAC();
            newBac.setTimestamp(bac.getTimeStamp());
            newBac.setConsumptionMetric(bac.getConsumptionMetric());
            newBac.setVolumeConsumption(bac.getVolumeConsumption());
            newBac.setPercentageConsumption(bac.getPercentageConsumption());
            bacs.add(newBac);
        }
        sortbyFileSize(bacs);
        return bacs;
    }

    private  void sortbyFileSize(List<BAC> Bacs)
    {
        Collections.sort(Bacs, new Comparator<BAC>() {
            public int compare(BAC o1, BAC o2) {
                return o1.getTimeStampIntValue() - o2.getTimeStampIntValue();
            }
        });
    }

    /***
     * Builder for the Datastore Singleton
     */
    public static class Builder {
        private final Context mContext;
        private String mDatabaseName = "elan";
        private boolean mDeleteOnInit = false;

        /**
         * Start building a new {@link RealmStore} instance.
         */
        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            mContext = context.getApplicationContext();
        }

        public Builder databaseName(String databaseName) {
            if (Internals.isEmpty(databaseName)) {
                throw new IllegalArgumentException("DatabaseName must not be null or empty.");
            }
            mDatabaseName = databaseName;
            return this;
        }

        public Builder deleteOnInit(boolean deleteOnInit) {
            mDeleteOnInit = deleteOnInit;
            return this;
        }

        /**
         * Create the {@link RealmStore} instance.
         */
        public RealmStore build() {
            return new RealmStore(mContext, mDatabaseName, mDeleteOnInit);
        }
    }
}
