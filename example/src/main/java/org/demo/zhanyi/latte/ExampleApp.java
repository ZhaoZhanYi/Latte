package org.demo.zhanyi.latte;

import android.app.Application;
import android.os.Handler;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.demo.latte.app.Latte;
import org.demo.latte.ec.database.DatabaseManager;
import org.demo.latte.ec.icon.FontEcModule;
import org.demo.latte.net.interceptors.DebugInterceptor;

/**
 * Created by feibai on 2017/8/1.
 */

public class ExampleApp extends Application {

    private static final Handler mHandler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .withInterceptor(new DebugInterceptor("indexdata", R.raw.indexdata))
                .withAppId("")
                .withAppSecret("")
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);
    }

    //app映射到web上的调试工具
    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );
    }
}
