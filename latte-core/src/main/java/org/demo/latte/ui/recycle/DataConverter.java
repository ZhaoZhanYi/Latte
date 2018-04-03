package org.demo.latte.ui.recycle;

import java.util.ArrayList;

/**
 * Created by zhanyi on 2017/11/29.
 */

public abstract class DataConverter {
    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    public abstract ArrayList<MultipleItemEntity> convert();

    private String mJsonData = null;

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }
}
