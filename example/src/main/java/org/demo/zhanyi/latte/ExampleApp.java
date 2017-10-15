package org.demo.zhanyi.latte;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.demo.latte.app.Latte;
import org.demo.latte.ec.icon.FontEcModule;
import org.demo.latte.net.interceptors.DebugInterceptor;

/**
 * Created by feibai on 2017/8/1.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
    }
}
