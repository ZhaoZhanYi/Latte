package org.demo.latte.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.demo.latte.delegates.bottom.BottomItemDelegate;
import org.demo.latte.ec.R;

/**
 * Created by zzl on 2018/5/29.
 */

public class ShopCartDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_shopcart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
