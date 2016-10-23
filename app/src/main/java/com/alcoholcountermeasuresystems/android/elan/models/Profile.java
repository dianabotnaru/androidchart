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

    public Profile() {
    }

    // TODO: Add getters/setters
}
