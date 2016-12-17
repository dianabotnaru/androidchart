package com.alcoholcountermeasuresystems.android.elan.utils;

import com.alcoholcountermeasuresystems.android.elan.data.enums.Country;
import com.alcoholcountermeasuresystems.android.elan.data.enums.MeasurementUnit;
import com.alcoholcountermeasuresystems.android.elan.models.Measurement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MeasurementLookup {

    public final static String CM = "cm";
    public final static String INCHES = "in";
    public final static String KG = "kg";
    public final static String LBS = "lbs";

    public static Map<String, Measurement> lookup = new HashMap<>();

    static {
        Map<String, Measurement> aMap = new HashMap<>();
        aMap.put(Country.Australia.name(), new Measurement(Country.Australia, MeasurementUnit.GramsPerDecilitre, 0.02, 0.05));
        aMap.put(Country.Brazil.name(), new Measurement(Country.Brazil, MeasurementUnit.MilligramsPerLitre, 0.05, 0.30));
        aMap.put(Country.Canada.name(), new Measurement(Country.Canada, MeasurementUnit.GramsPerDecilitre, 0.01, 0.05));
        aMap.put(Country.Denmark.name(), new Measurement(Country.Denmark, MeasurementUnit.GramsPerLitre, 0.10, 0.20));
        aMap.put(Country.Finland.name(), new Measurement(Country.Finland, MeasurementUnit.GramsPerLitre, 0.10, 0.20));
        aMap.put(Country.France.name(), new Measurement(Country.France, MeasurementUnit.MilligramsPerLitre, 0.10, 0.25));
        aMap.put(Country.Japan.name(), new Measurement(Country.Japan, MeasurementUnit.GramsPerLitre, 0.80, 1.5));
        aMap.put(Country.Norway.name(), new Measurement(Country.Norway, MeasurementUnit.GramsPerLitre, 0.10, 0.20));
        aMap.put(Country.Portugal.name(), new Measurement(Country.Portugal, MeasurementUnit.MilligramsPerLitre, 0.05, 0.15));
        aMap.put(Country.Russia.name(), new Measurement(Country.Russia, MeasurementUnit.MilligramsPerLitre, 0.05, 0.15));
        aMap.put(Country.Spain.name(), new Measurement(Country.Spain, MeasurementUnit.MilligramsPerLitre, 0.05, 0.25));
        aMap.put(Country.Sweden.name(), new Measurement(Country.Sweden, MeasurementUnit.GramsPerLitre, 0.10, 0.20));
        aMap.put(Country.USA.name(), new Measurement(Country.USA, MeasurementUnit.GramsPerDecilitre, 0.05, 0.08));
        lookup = Collections.unmodifiableMap(aMap);
    }
}
