package org.demo.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;

import org.demo.latte.delegates.bottom.BaseBottomDelegate;
import org.demo.latte.delegates.bottom.BottomItemDelegate;
import org.demo.latte.ec.R;
import org.demo.latte.ec.R2;

import butterknife.BindView;

/**
 * Created by zhanyi on 2017/11/15.
 */

public class SortDelegate extends BottomItemDelegate {

    // 左边菜单的容器
    @BindView(R2.id.cfl_menu_container)
    ContentFrameLayout mMenuContainer;
    // 菜单对应的右边详情容器
    @BindView(R2.id.cfl_detail_container)
    ContentFrameLayout mDetailContainer;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final MenuDelegate menuDelegate = new MenuDelegate();
        final ContentDelegate contentDelegate = new ContentDelegate();

//        加载菜单item
        loadRootFragment(R.id.cfl_menu_container, menuDelegate);

    }
}
