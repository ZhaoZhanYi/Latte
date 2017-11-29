package org.demo.latte.ui.recycle;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by zhanyi on 2017/11/29.
 */

public class MultipleItemEntityBuilder {

    private static final LinkedHashMap<Object, Object> FILEDS = new LinkedHashMap<>();

    public MultipleItemEntityBuilder() {
        // 首先，清除之前的数据
        FILEDS.clear();
    }

    public final MultipleItemEntityBuilder setItemType(int itemType) {
        FILEDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public final MultipleItemEntityBuilder setField(Object key, Object value) {
        FILEDS.put(key, value);
        return this;
    }

    public final MultipleItemEntityBuilder setField(LinkedHashMap<?, ?> map) {
        FILEDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build() {
        return new MultipleItemEntity(FILEDS);
    }
}
