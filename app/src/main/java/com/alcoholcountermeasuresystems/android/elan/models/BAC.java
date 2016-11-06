package com.alcoholcountermeasuresystems.android.elan.models;

import org.joda.time.DateTime;

import java.util.Date;

import io.realm.RealmObject;

public class BAC extends RealmObject {

    private String id;
    private Date timestamp;
    private double bacDataPoint;
    private double volumeConsumption;
    private double percentageConsumption;
    private boolean isFromDevice;

    public BAC() {
    }

    // TODO: Getters/Setters

    public Date getTimeStamp(){
        return timestamp;
    }

    public void setTimestamp(Date timestamp){
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

    public void setVolumeConsumption(double bacDataPoint){
        this.volumeConsumption = volumeConsumption;
    }

    public double getPercentageConsumption(){
        return percentageConsumption;
    }

    public void setPercentageConsumption(double percentageConsumption){
        this.percentageConsumption = percentageConsumption;
    }

    public boolean getIsFromDevice(){
        return isFromDevice;
    }

    public void setIsFromDevice(boolean isFromDevice){
        this.isFromDevice = isFromDevice;
    }

}
