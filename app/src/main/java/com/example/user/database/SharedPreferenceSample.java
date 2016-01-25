package com.example.user.database;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 01-12-2015.
 */
public class SharedPreferenceSample extends Activity {

    final static String DEFAULT = "Default";
    SharedPreferences sp = null;
    SharedPreferences.Editor editor = null;

    public SharedPreferenceSample(Context context, String fileName) {

        sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void saveData(String key, String value) {

        editor.putString(key, value);
        editor.commit();
    }

    public String getData (String key) {

        return sp.getString(key, SharedPreferenceSample.DEFAULT);

    }
}
