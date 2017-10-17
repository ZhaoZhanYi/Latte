package org.demo.zhanyi.latte;

import org.demo.latte.activities.ProxyActivity;
import org.demo.latte.delegates.LatteDelegate;
import org.demo.latte.ec.launcher.LauncherDelegate;
import org.demo.latte.ec.launcher.LauncherScrollDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherScrollDelegate();
    }


}
