package org.demo.latte.ui.banner;


import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.demo.latte.app.Latte;


/**
 * Created by zzl on 2017/12/5.
 */

public class ImageHolder extends Holder<String> {

    private AppCompatImageView mImageView;

    private RequestOptions mRequestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().centerCrop();


    public ImageHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        mImageView = (AppCompatImageView) itemView;
    }

    @Override
    public void updateUI(String data) {
        Glide.with(Latte.getApplicationContext())
                .load(data)
                .apply(mRequestOptions)
                .into(mImageView);
    }
}
