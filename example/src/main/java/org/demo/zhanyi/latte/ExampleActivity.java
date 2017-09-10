package org.demo.zhanyi.latte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.demo.latte.activities.ProxyActivity;
import org.demo.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

}
