package com.henote.lastfm.util;

/**
 * Created by Naveen on 27/10/2017.
 */
public class Validation {

    public static boolean isEmpty(String strValue) {
        if (strValue != null && strValue.length() > 0)
            return true;
        else
            return false;
    }

    public static boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


}
