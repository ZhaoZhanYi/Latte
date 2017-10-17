package org.demo.latte.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by zhanyi on 2017/10/5.
 *
 * 由于 AVLoadingIndicatorView 使用的是反射加载动画drawable，
 * 反射对系统资源消耗比较大，写这个类，在第一次反射加载动画drawable时，
 * 把它保存进静态Map里，这样就可以在需要用到 AVLoadingIndicatorView 时，
 * 不必每次反射加载，直接在Map里取就可以了，节省系统资源
 *
 */

public final class LoaderCreator {

    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        // Map里取不到则时第一次加载，然后保存进Map
        if (LOADING_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    /**
     * 以drawable的名称获取drawable实例
     * @param name drawable的名称
     * @return drawable的实例
     */
    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        if(!name.contains(".")) {
            //drawable都在此包之下
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

}
