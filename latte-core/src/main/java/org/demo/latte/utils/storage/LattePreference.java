package org.demo.latte.utils.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;

import org.demo.latte.app.Latte;

/**
 * Created by zhanyi on 2017/10/15.
 */

public class LattePreference {

    private static final String APP_PREFERENCES_KEY = "profile";

    /**
     * 提示:
     * Activity.getPreferences(int mode)生成 Activity名.xml 用于Activity内部存储
     * PreferenceManager.getDefaultSharedPreferences(Context)生成 包名_preferences.xml
     * Context.getSharedPreferences(String name,int mode)生成name.xml
     */
    private static final SharedPreferences PREFEREMCES =
            PreferenceManager.getDefaultSharedPreferences(Latte.getApplicationContext());

//    private static final SharedPreferences getPreferemces = Latte.getApplicationContext().getSharedPreferences("profile", Latte.getApplicationContext().MODE_PRIVATE);

    private static SharedPreferences getAppPreference() {
        return PREFEREMCES;
    }

    //设置profile
    public static void setAPPProfile(String value) {
        getAppPreference().edit()
                .putString(APP_PREFERENCES_KEY, value)
                .apply();
    }

    //删除profile
    public static void removeAppProfile() {
        getAppPreference().edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    //获取profile
    public static String getAppProfile() {
        return getAppPreference().getString(APP_PREFERENCES_KEY, null);
    }

    //获取profile并JSON解析
    public static com.alibaba.fastjson.JSONObject getAppProfileJson() {
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    //清空Preferences
    public static void clearAppPreferences() {
        getAppPreference().edit()
                .clear()
                .apply();
    }

    //设置App标记
    public static void setAppFlag(String key, boolean flag) {
        getAppPreference().edit()
                .putBoolean(key, flag)
                .apply();
    }

    //获取App标记
    public static boolean getAppFlag(String key) {
        return getAppPreference().getBoolean(key, false);
    }

    //添加自定义的App Profile
    public static void addCustomAppProfile(String key, String value) {
        getAppPreference().edit()
                .putString(key, value)
                .apply();
    }

    //获取自定义的App Profile
    public static String getCustomAppProfile(String key) {
        return getAppPreference().getString(key, "");
    }
}
