package org.demo.latte.ec.main.index;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;

import org.demo.latte.app.Latte;

/**
 * Created by zhanyi on 2017/11/25.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout mSwipeRefreshLayout;

    private static final Handler HANDLER = new Handler();

    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout) {
        this.mSwipeRefreshLayout = swipeRefreshLayout;
//        this.mSwipeRefreshLayout.setNestedScrollingEnabled(true);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);

    }
}
