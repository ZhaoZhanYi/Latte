package org.demo.latte.ec.main.index;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.demo.latte.net.RestClient;
import org.demo.latte.net.callback.IError;
import org.demo.latte.net.callback.IFailure;
import org.demo.latte.net.callback.IRequest;
import org.demo.latte.net.callback.ISuccess;
import org.demo.latte.ui.recycle.DataConverter;
import org.demo.latte.ui.recycle.MultipleRecycleAdapter;
import org.demo.latte.ui.refresh.PagingBean;

/**
 * Created by zhanyi on 2017/11/25.
 */

public class RefreshHandler implements
        SwipeRefreshLayout.OnRefreshListener
        , BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout mSwipeRefreshLayout;

    private final PagingBean BEAN;

    private final RecyclerView mRecyclerView;

    private MultipleRecycleAdapter mAdapter;

    private final DataConverter converter;

    private static final Handler HANDLER = new Handler();

    private RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                           RecyclerView recyclerView,
                           DataConverter converter,
                           PagingBean BEAN) {
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        this.mRecyclerView = recyclerView;
        this.converter = converter;
        this.BEAN = BEAN;
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter converter,
                                        PagingBean BEAN) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, converter, BEAN);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);

    }

    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.i("onSuccess", response);
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        //设置Adapter
                        mAdapter = MultipleRecycleAdapter.create(converter.setJsonData(response));
//                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, mRecyclerView);
                        Log.i("onSuccess01", (mAdapter == null ? true : false) + "");
                        Log.i("onSuccess02", mRecyclerView.hashCode() + "");
                        mRecyclerView.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {

                    }
                })
                .params("", "")
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

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
