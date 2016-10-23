package com.alcoholcountermeasuresystems.android.elan.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class SubscriptionManager {

    private static volatile SubscriptionManager singleton = null;

    private CompositeSubscription mSubscription = new CompositeSubscription();
    private Subscription mRtspSubscription;

    Context mContext;
    RealmStore mRealmStore;
    SharedPreferences mSharedPreferences;

    /***
     * Method to get the Datastore instance
     *
     * @param context Activity or Application Context
     * @return A static instance of Datastore
     */
    public static SubscriptionManager with(Context context) {
        if (singleton == null) {
            synchronized (SubscriptionManager.class) {
                if (singleton == null) {
                    singleton = new Builder(context).build();
                }
            }
        }
        return singleton;
    }

    private SubscriptionManager(@NonNull Context context,
                                @NonNull RealmStore realmStore,
                                @NonNull SharedPreferences sharedPreferences) {
        // Grab the application context in case an activity context was passed in
        mContext = context;
        mRealmStore = realmStore;
        mSharedPreferences = sharedPreferences;
    }

    public void initialize() {

    }

    public void uninitialize() {
        mSubscription.clear();
        if (mRtspSubscription != null) {
            mRtspSubscription.unsubscribe();
            mRtspSubscription = null;
        }
    }

    //region Builder

    /**
     * Set the global instance returned from {@link #with}.
     * <p>
     * This method must be called before any calls to {@link #with} and may only be called once.
     */
    public static void setSingletonInstance(SubscriptionManager subscriptionManager) {
        if (subscriptionManager == null) {
            throw new IllegalArgumentException("SubscriptionManager must not be null.");
        }
        synchronized (SubscriptionManager.class) {
            if (singleton != null) {
                throw new IllegalStateException("Singleton instance already exists.");
            }
            singleton = subscriptionManager;
        }
    }

    /***
     * Builder for the SubscriptionManager Singleton
     */
    public static class Builder {
        private final Context mContext;
        private RealmStore mRealmStore;
        private SharedPreferences mSharedPreferences;

        /**
         * Start building a new {@link SubscriptionManager} instance.
         */
        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            mContext = context.getApplicationContext();
        }

        public Builder realmStore(@NonNull RealmStore realmStore) {
            mRealmStore = realmStore;
            return this;
        }

        public Builder sharedPreferences(@NonNull SharedPreferences sharedPreferences) {
            mSharedPreferences = sharedPreferences;
            return this;
        }

        /**
         * Create the {@link SubscriptionManager} instance.
         */
        public SubscriptionManager build() {
            return new SubscriptionManager(mContext, mRealmStore, mSharedPreferences);
        }
    }

    //endregion
}
