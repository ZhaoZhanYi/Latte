package org.demo.latte.utils.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;

import org.demo.latte.app.Latte;

/**
 * Created by zhanyi on 2017/10/15.
 */

public class LattePreference {

    private static final SharedPreferences PREFEREMCES =
            PreferenceManager.getDefaultSharedPreferences(Latte.getApplicationContext());
//    private static final SharedPreferences getPreferemces = PreferenceManager.

    private static final String APP_PREFERENCES_KEY = "profile";

    private static SharedPreferences getAppPreference() {
        return PREFEREMCES;
    }

    public static void setAPPProfile(String value) {
        getAppPreference().edit()
                .putString(APP_PREFERENCES_KEY, value)
                .apply();
    }

    public static String getAppProfile() {
        return getAppPreference().getString(APP_PREFERENCES_KEY, null);
    }

    public static com.alibaba.fastjson.JSONObject getAppProfileJson() {
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }
}
