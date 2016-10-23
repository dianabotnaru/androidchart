package com.alcoholcountermeasuresystems.android.elan.models;

import org.joda.time.DateTime;

import io.realm.RealmObject;

public class BAC extends RealmObject {

    private String id;
    private DateTime timestamp;
    private double bacDataPoint;
    private double volumeConsumption;
    private double percentageConsumption;
    private boolean isFromDevice;

    public BAC() {
    }

    // TODO: Getters/Setters
}
