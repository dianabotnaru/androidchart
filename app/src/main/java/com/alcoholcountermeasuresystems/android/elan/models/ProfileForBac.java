package com.alcoholcountermeasuresystems.android.elan.models;

/**
 * Created by jordi on 04/11/16.
 */

public class ProfileForBac {
    private int age = 0;
    private String gender = "";    // M | F
    private int height = 0 ;
    private String heightMetric = "";  //  cm | in
    private int weight = 0;
    private String weightMetric = ""; //  lb | kg
    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public int getHeight(){
        return height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public String getHeightMetric(){
        return heightMetric;
    }

    public void setHeightMetric(String heightMetric){
        this.heightMetric = heightMetric;
    }

    public int getWeight(){
        return weight;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public String getWeightMetric(){
        return weightMetric;
    }

    public void setWeightMetric(String weightMetric){
        this.weightMetric = weightMetric;
    }

    public void save() {
        //TODO: save model in local
    }
}
