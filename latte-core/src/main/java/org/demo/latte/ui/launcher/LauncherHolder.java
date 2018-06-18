package org.demo.latte.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

import org.demo.latte.app.Latte;

/**
 * Created by zhanyi on 2017/10/17.
 */

public class LauncherHolder extends Holder<Integer> {

    private AppCompatImageView mImageView;

    public LauncherHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        mImageView = (AppCompatImageView) itemView;
    }

    @Override
    public void updateUI(Integer data) {
        Log.d("====updateUI=====", data.toString());
        mImageView.setBackgroundResource(data);
    }

}
