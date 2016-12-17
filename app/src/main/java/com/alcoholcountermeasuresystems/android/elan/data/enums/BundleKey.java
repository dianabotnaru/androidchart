package com.alcoholcountermeasuresystems.android.elan.data.enums;

/**
 * Created by jordi on 09/11/16.
 */

public enum BundleKey {
    KeyBac("bac"),
    KeyIsComeHistory("is_coming_from_history"),
    KeyIsComeInformation("is_coming_from_information");

    private final String mName;

    BundleKey(String name) {
        mName = name;
    }

    public String toString() {
        return mName;
    }
}
