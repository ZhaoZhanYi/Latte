package org.demo.zhanyi.latte;

import org.demo.latte.activities.ProxyActivity;
import org.demo.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

}
