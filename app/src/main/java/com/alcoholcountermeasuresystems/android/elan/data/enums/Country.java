package com.alcoholcountermeasuresystems.android.elan.data.enums;

public enum Country {
    Australia,
    Brazil,
    Canada,
    Denmark,
    Finland,
    France,
    Japan,
    Norway,
    Portugal,
    Russia,
    Spain,
    Sweden,
    USA;

    public static String[] names() {
        Country[] countries = values();
        String[] names = new String[countries.length];

        for (int i = 0; i < countries.length; i++) {
            names[i] = countries[i].name();
        }

        return names;
    }
}