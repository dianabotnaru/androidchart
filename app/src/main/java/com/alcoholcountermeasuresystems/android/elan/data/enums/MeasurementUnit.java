package com.alcoholcountermeasuresystems.android.elan.data.enums;

/**
 * Created by sureshjoshi on 2016-10-23.
 */

public enum MeasurementUnit {
    GramsPerDecilitre("g/dL"),
    GramsPerLitre("g/L"),
    MilligramsPerDeciLitre("mg/dL"),
    MilligramsPerLitre("mg/L"),
    Milliter("ml"),
    FluidOunce("oz");

    private final String mName;

    MeasurementUnit(String name) {
        mName = name;
    }

    public String toString() {
        return mName;
    }
}