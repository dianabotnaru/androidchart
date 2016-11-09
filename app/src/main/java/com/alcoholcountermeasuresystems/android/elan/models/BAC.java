package com.alcoholcountermeasuresystems.android.elan.models;

import org.joda.time.DateTime;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class BAC extends RealmObject {

    private long timestamp;
    private double bacDataPoint;
    private double volumeConsumption;
    private double percentageConsumption;
    private boolean isFromDevice;
    private String consumptionMetric;

    public BAC() {
    }

    // TODO: Getters/Setters

    public long getTimeStamp(){
        return timestamp;
    }

    public int getTimeStampIntValue(){
        return (int)timestamp;
    }

    public void setTimestamp(long timestamp){
        this.timestamp = timestamp;
    }

    public double getBacDataPoint(){
        return bacDataPoint;
    }

    public void setBacDataPoint(double bacDataPoint){
        this.bacDataPoint = bacDataPoint;
    }

    public double getVolumeConsumption(){
        return volumeConsumption;
    }

    public void setVolumeConsumption(double volumeConsumption){
        this.volumeConsumption = volumeConsumption;
    }

    public double getPercentageConsumption(){
        return percentageConsumption;
    }

    public void setPercentageConsumption(double percentageConsumption){
        this.percentageConsumption = percentageConsumption;
    }
    public void setConsumptionMetric(String consumptionMetric){
        this.consumptionMetric = consumptionMetric;
    }

    public String getConsumptionMetric(){
        return consumptionMetric;
    }

    public boolean getIsFromDevice(){
        return isFromDevice;
    }

    public void setIsFromDevice(boolean isFromDevice){
        this.isFromDevice = isFromDevice;
    }

}
