package org.demo.latte.app;

import java.util.WeakHashMap;

import static android.R.attr.key;

/**
 * Created by feibai on 2017/8/1.
 */

/**
 *  单例模式，饿汉
 */
public class Configurator {
    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public final void configure() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call config");
        }
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    final WeakHashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 私有静态内部类
     * 由于是私有，只能被其外部类创建和引用
     *
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

}
