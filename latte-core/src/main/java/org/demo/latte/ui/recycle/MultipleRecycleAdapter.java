package org.demo.latte.ui.recycle;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.demo.latte.R;
import org.demo.latte.ui.banner.BannerCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzl on 2017/12/5.
 */

public class MultipleRecycleAdapter extends
        BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup, OnItemClickListener {

    private boolean mIsInitBanner = false;

    private RequestOptions mRequestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().centerCrop();
//    List<MultipleItemEntity> mData;

    public MultipleRecycleAdapter(List<MultipleItemEntity> data) {
        super(data);
//        mData = data;
        init();
    }

    public static MultipleRecycleAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecycleAdapter(data);
    }

    public static MultipleRecycleAdapter create(DataConverter converter) {
        return new MultipleRecycleAdapter(converter.convert());
    }

    //设置不同的布局
    private void init() {
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        Log.i("===adapter convert==", holder.getItemViewType() + "");
        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = entity.getField(MultipleFields.TEXT);
                Log.i("===TEXT", text);
                holder.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                Log.i("===IMAGE", imageUrl);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(mRequestOptions)
                        .into((ImageView) holder.getView(R.id.img_single));
                break;
            case ItemType.TEXT_IMAGE:
                text = entity.getField(MultipleFields.TEXT);
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                Log.i("===TEXT_IMAGE", text + " : " + imageUrl);
                holder.setText(R.id.tv_multiple, text);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(mRequestOptions)
                        .into((ImageView) holder.getView(R.id.img_multiple));
                break;
            case ItemType.BANNER:
                Log.i("===BANNER", "");

                if (!mIsInitBanner) {
                    bannerImages = entity.getField(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }
                break;
            default:
                Log.i("===default", "");

                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        Log.i("getSpanSize", position + "");
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }


    @Override
    public void onItemClick(int i) {

    }
}
