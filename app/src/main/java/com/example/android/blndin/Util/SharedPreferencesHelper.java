package com.example.android.blndin.Util;

import android.content.Context;
import android.content.SharedPreferences;

import static com.example.android.blndin.Util.Constants.PREF_KEY;

/**
 * Created by LeGenÐ on 5/12/2018.
 */

public class SharedPreferencesHelper {

    public static void storeDataToSharedPref(Context context, String data, String key) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedpreferences.edit();
        prefsEditor.putString(key, data);
        prefsEditor.apply();
    }

    public static String retrieveDataFromSharedPref(Context context, String key) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        String json = sharedpreferences.getString(key, null);
        return json;
    }

    public static void clearAllSavedSharedData(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedpreferences.edit();
        prefsEditor.clear();
        prefsEditor.apply();
    }
}
