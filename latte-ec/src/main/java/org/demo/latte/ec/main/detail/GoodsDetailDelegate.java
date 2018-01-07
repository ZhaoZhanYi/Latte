package org.demo.latte.ec.main.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.demo.latte.delegates.LatteDelegate;
import org.demo.latte.ec.R;

/**
 * Created by zzl on 2018/1/7.
 */

public class GoodsDetailDelegate extends LatteDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
