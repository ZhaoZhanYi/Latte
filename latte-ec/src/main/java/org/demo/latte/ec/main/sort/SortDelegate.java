package org.demo.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.demo.latte.delegates.bottom.BaseBottomDelegate;
import org.demo.latte.delegates.bottom.BottomItemDelegate;
import org.demo.latte.ec.R;

/**
 * Created by zhanyi on 2017/11/15.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
