package org.demo.latte.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

import org.demo.latte.delegates.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zhanyi on 2017/11/14.
 */

public abstract class BaseBottomDelegate extends LatteDelegate {

    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();

    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();

    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    // 表示当前是哪一个 delegate
    private int mCurrentDelegate = 0;

    // 表示第一个展示的 delegate
    private int mIndexDelegate = 0;

    private int mClickedColor = Color.RED;

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();
        // 获取items，子类重写setItems
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        // TODO: 2017/11/14 为什么不直接 ITEMS = setItems(builder) ??????
        ITEMS.putAll(items);

        // 遍历Map
        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }



    }
}
