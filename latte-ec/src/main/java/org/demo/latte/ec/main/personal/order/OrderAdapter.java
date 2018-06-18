package org.demo.latte.ec.main.personal.order;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.demo.latte.ec.R;

import java.util.List;

public class OrderAdapter extends BaseQuickAdapter<OrderBean, OrderHolder> {

    private static final RequestOptions mRequestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().centerCrop();


    public OrderAdapter(int layoutResId, @Nullable List<OrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(OrderHolder orderHolder, OrderBean orderBean) {
        Glide.with(mContext)
                .load(orderBean.getImage())
                .apply(mRequestOptions)
                .into((ImageView) orderHolder.getView(R.id.iv_goods_img));
        orderHolder.setText(R.id.tv_goods_title, orderBean.getTitle());
        orderHolder.setText(R.id.tv_goods_price, orderBean.getPrice());
        orderHolder.setText(R.id.tv_order_time, orderBean.getTime());
    }
}
