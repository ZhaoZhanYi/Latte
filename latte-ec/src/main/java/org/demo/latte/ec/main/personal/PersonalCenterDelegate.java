package org.demo.latte.ec.main.personal;

import android.os.Bundle;
import android.view.View;

import org.demo.latte.delegates.bottom.BottomItemDelegate;
import org.demo.latte.ec.R;

public class PersonalCenterDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_personalcenter;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
