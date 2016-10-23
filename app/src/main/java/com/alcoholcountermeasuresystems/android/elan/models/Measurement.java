package com.alcoholcountermeasuresystems.android.elan.models;

import com.alcoholcountermeasuresystems.android.elan.data.enums.Country;
import com.alcoholcountermeasuresystems.android.elan.data.enums.MeasurementUnit;
import com.alcoholcountermeasuresystems.android.elan.data.enums.Outcome;

public class Measurement {

    private final Country mCountry;
    private final MeasurementUnit mUnit;
    private final Double mWarningLevel;
    private final Double mFailLevel;

    public Country getCountry() {
        return mCountry;
    }

    public MeasurementUnit getUnit() {
        return mUnit;
    }

    public Double getWarningLevel() {
        return mWarningLevel;
    }

    public Double getFailLevel() {
        return mFailLevel;
    }

    public Measurement(Country country, MeasurementUnit unit, Double warningLevel, Double failLevel) {
        mCountry = country;
        mUnit = unit;
        mWarningLevel = warningLevel;
        mFailLevel = failLevel;
    }

    /***
     * Converts the ACS BTI BAC value to an 'actual' BAC value (e.g. divide by 1000)
     *
     * @param btiBac The BAC value as an int coming straight from ACS BTI hardware (e.g. inherently bac*1000)
     * @return
     */
    public Double getBacFromBti(int btiBac) {
        return btiBac / 1000.0;
    }

    /***
     * Returns the BAC value, localized to the country's expected values (e.g. for Canada, a BAC
     * of 25 returns 0.025)
     *
     * @param btiBac The BAC value as an int coming straight from ACS BTI hardware (e.g. inherently bac*1000)
     * @return The BAC value localized to the country
     */
    public Double getLocalizedBacFromBti(int btiBac) {
        // Conversion the BAC to the appropriate per-country result
        Double localizedBac = getBacFromBti(btiBac);
        localizedBac = getLocalizedBac(localizedBac);
        return localizedBac;
    }

    /***
     * @param bac
     * @return
     */
    public Double getLocalizedBac(Double bac) {
        Double localizedBac = bac;
        switch (mUnit) {
            case GramsPerDecilitre:
                localizedBac /= 1000.0;
                break;
            case GramsPerLitre:
                localizedBac /= 100.0;
                break;
            case MilligramsPerDeciLitre:
                break;
            case MilligramsPerLitre:
                localizedBac /= 210.0;
                break;
        }
        return localizedBac;
    }

    public Outcome evaluate(int btiBac) {
        Double localizedBac = getLocalizedBacFromBti(btiBac);

        // Check BAC against country's pass/warning/fail levels
        if (localizedBac >= mFailLevel) {
            return Outcome.Fail;
        } else if (localizedBac >= mWarningLevel) {
            return Outcome.Warning;
        } else {
            return Outcome.Pass;
        }
    }

}
