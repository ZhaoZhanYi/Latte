package org.demo.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.demo.latte.delegates.bottom.BottomItemDelegate;
import org.demo.latte.ec.R;
import org.demo.latte.ec.R2;
import org.demo.latte.ec.main.EcBottomDelegate;
import org.demo.latte.ui.recycle.DividerDecoration;
import org.demo.latte.ui.recycle.MultipleFields;
import org.demo.latte.ui.recycle.MultipleItemEntity;
import org.demo.latte.ui.recycle.MultipleRecycleAdapter;
import org.demo.latte.ui.refresh.PagingBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhanyi on 2017/10/15.
 */

public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.sr_index)
    SwipeRefreshLayout mRefreshLayout = null;

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;

    private RefreshHandler mRefreshHandler = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout,
                mRecyclerView,
                new IndexDataConverter(),
                new PagingBean());

        Log.i("==onBindView===", mRecyclerView.hashCode() + "");

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecycleView();
        mRefreshHandler.firstPage("http://127.0.0.1/indexdata");
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecycleView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(
                new DividerDecoration(ContextCompat.getColor(getContext(), R.color.divider), 5));
        EcBottomDelegate ecBottomDelegate = getParentDelegate();
//        mRecyclerView.setRecyclerListener(IndexItemClickListener.create(ecBottomDelegate));
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }
}
