package org.demo.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.demo.latte.delegates.bottom.BottomItemDelegate;
import org.demo.latte.ec.R;
import org.demo.latte.ec.R2;

import butterknife.BindView;

/**
 * Created by zhanyi on 2017/10/15.
 */

public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.sr_index)
    SwipeRefreshLayout mRefreshLayout = null;

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRefreshLayout();
        RefreshHandler refreshHandler = new RefreshHandler(mRefreshLayout);
        refreshHandler.onRefresh();
    }

    private void initRefreshLayout() {
        mRefreshLayout.setProgressViewOffset(true, 200, 300);
    }
}
