package org.demo.zhanyi.latte;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.demo.latte.delegates.LatteDelegate;

/**
 * Created by feibai on 2017/9/10.
 */

public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
