package org.demo.latte.ui.recycle;

import android.support.annotation.ColorInt;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * Created by zzl on 2017/12/17.
 */

public class DividerDecoration extends DividerItemDecoration{

    public DividerDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookup(color, size));
    }


    public static class DividerLookup implements DividerItemDecoration.DividerLookup {

        private int color;

        private int size;

        public DividerLookup(int color, int size) {
            this.color = color;
            this.size = size;
        }

        @Override
        public Divider getVerticalDivider(int position) {
            return new Divider.Builder()
                    .color(color)
                    .size(size)
                    .build();
        }

        @Override
        public Divider getHorizontalDivider(int position) {
            return new Divider.Builder()
                    .color(color)
                    .size(size)
                    .build();
        }
    }

}
