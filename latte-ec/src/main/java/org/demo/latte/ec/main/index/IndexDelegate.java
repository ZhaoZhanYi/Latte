package org.demo.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.demo.latte.delegates.LatteDelegate;
import org.demo.latte.delegates.bottom.BottomItemDelegate;
import org.demo.latte.ec.R;

/**
 * Created by zhanyi on 2017/10/15.
 */

public class IndexDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
