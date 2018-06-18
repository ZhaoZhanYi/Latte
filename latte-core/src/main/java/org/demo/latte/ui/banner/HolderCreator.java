package org.demo.latte.ui.banner;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import org.demo.latte.R;

/**
 * Created by zzl on 2017/12/6.
 */

public class HolderCreator implements CBViewHolderCreator {


    @Override
    public Holder createHolder(View itemView) {
        return new ImageHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_banner_image;
    }
}
