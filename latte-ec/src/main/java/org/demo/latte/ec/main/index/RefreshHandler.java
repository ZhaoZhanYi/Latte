package org.demo.latte.ec.main.index;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import org.demo.latte.app.Latte;
import org.demo.latte.net.RestClient;
import org.demo.latte.net.callback.IError;
import org.demo.latte.net.callback.IFailure;
import org.demo.latte.net.callback.IRequest;
import org.demo.latte.net.callback.ISuccess;

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
                RestClient.builder()
                        .url("http://127.0.0.1/indexdata")
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                Toast.makeText(Latte.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                                mSwipeRefreshLayout.setRefreshing(false);

                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure() {
                                mSwipeRefreshLayout.setRefreshing(false);

                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int code, String message) {
                                mSwipeRefreshLayout.setRefreshing(false);

                            }
                        })
                        .request(new IRequest() {
                            @Override
                            public void onRequestStart() {

                            }

                            @Override
                            public void onRequestEnd() {

                            }
                        })
                        .build()
                        .get();

//                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);

    }
}
