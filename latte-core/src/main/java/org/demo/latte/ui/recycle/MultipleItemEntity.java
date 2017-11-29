package org.demo.latte.ui.recycle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by zhanyi on 2017/11/29.
 */

public class MultipleItemEntity implements MultiItemEntity {

    // TODO: 2017/11/29 引用队列，查看相关用法
    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUENE = new ReferenceQueue<>();

    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();

    // TODO: 2017/11/29 软引用，查看相关用法
    private final SoftReference<LinkedHashMap<Object, Object>> FILEDS_REFERENCE =
            new SoftReference<LinkedHashMap<Object, Object>>(MULTIPLE_FIELDS, ITEM_QUENE);

    public MultipleItemEntity(LinkedHashMap<Object, Object> map) {
        FILEDS_REFERENCE.get().putAll(map);
    }

    @Override
    public int getItemType() {
        return (int) FILEDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key) {
        return (T) FILEDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?, ?> getFileds() {
        return FILEDS_REFERENCE.get();
    }

    public final MultipleItemEntity setField(Object key, Object value) {
        FILEDS_REFERENCE.get().put(key, value);
        return this;
    }

    public static MultipleItemEntityBuilder builder() {
        return new MultipleItemEntityBuilder();
    }


}
