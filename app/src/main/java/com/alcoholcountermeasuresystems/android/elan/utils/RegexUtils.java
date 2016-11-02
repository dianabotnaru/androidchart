package com.alcoholcountermeasuresystems.android.elan.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils
{
    private static final String TAG = RegexUtils.class.getCanonicalName();

    public static class RegEx
    {
        public static String EMAIL = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";
        public static String NUMBER = "^[0-9]+?$";
        public static String ALPHA = "^[A-Za-z]+$";
        public static String ALPHANUMERIC = "^[A-Za-z0-9]+$";
    }

    public static boolean validateEmail(String emailTextToCheck)
    {
        Pattern pattern = Pattern.compile(RegEx.EMAIL);
        Matcher matcher = pattern.matcher(emailTextToCheck);
        return matcher.matches();
    }
}
