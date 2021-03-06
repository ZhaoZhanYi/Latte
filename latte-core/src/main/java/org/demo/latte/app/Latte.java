package org.demo.latte.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by feibai on 2017/8/1.
 */

public final class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Object getConfiguration(Enum e) {
        return Configurator.getInstance().getLatteConfigs().get(e.name());
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static final Context getApplicationContext() {
         return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }



}
