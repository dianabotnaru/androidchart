package com.alcoholcountermeasuresystems.android.elan.models;

import io.realm.RealmObject;

public class Profile extends RealmObject {
    private int id = 0;
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String country = "";
    private String language = "";
    private String serialNumber = "";
    private String purchased = "";
    private String location = "";
    // TODO: Should this be split off into a separate model for Tests?
//    private String age : Int = 0
//    private String gender = ""    // M | F
//    private String height : Int = 0
//    private String heightMetric : String = ""  //  cm | in
//    private String weight :  Int = 0
//    private String weightMetric: String = "" //  lb | kg
//    private String isRegistered : Bool = false

    /**
     * Getters/Setters
     */

    public Profile() {
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getLanguage(){
        return language;
    }

    public void setLanguage(String language){
        this.language = language;
    }

    public String getSerialNumber(){
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber;
    }

    public String getPurchased(){
        return purchased;
    }

    public void setPurchased(String purchased){
        this.purchased = purchased;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }
}
