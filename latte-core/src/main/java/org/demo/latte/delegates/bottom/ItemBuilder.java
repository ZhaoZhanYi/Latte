package org.demo.latte.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * 简单工厂模式，生产底部导航的item
 * Created by zhanyi on 2017/11/14.
 */

public class ItemBuilder {

    // ITEMS 用来表述 BottomTabBean 与 BottomItemDelegate 之间的联系
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    protected static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BottomItemDelegate> build() {
        return ITEMS;
    }

}
