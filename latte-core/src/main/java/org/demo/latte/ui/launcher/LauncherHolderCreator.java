package org.demo.latte.ui.launcher;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import org.demo.latte.R;

/**
 * Created by zhanyi on 2017/10/17.
 */

public class LauncherHolderCreator implements CBViewHolderCreator {

    @Override
    public Holder createHolder(View itemView) {
        return new LauncherHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_banner_image;
    }
}
