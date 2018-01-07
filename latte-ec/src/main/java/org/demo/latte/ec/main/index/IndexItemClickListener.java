package org.demo.latte.ec.main.index;

import android.support.v7.widget.RecyclerView.SimpleOnItemTouchListener;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import org.demo.latte.delegates.LatteDelegate;
import org.demo.latte.ec.main.detail.GoodsDetailDelegate;

/**
 * Created by zzl on 2018/1/7.
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final LatteDelegate delegate;

    /**
     * 私有构造方法
     * @param delegate
     */
    private IndexItemClickListener (LatteDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * 简单工厂方法
     * @param delegate
     * @return
     */
    public static IndexItemClickListener create(LatteDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }


    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        delegate.start(new GoodsDetailDelegate());
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }
}
