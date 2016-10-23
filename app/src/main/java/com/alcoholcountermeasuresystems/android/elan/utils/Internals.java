package com.alcoholcountermeasuresystems.android.elan.utils;

// Strictly an internal class - not meant to be exposed
public class Internals {

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean equals(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }
}

