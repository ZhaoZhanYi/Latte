package org.demo.latte.ec.main.index;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.demo.latte.ui.recycle.DataConverter;
import org.demo.latte.ui.recycle.ItemType;
import org.demo.latte.ui.recycle.MultipleFields;
import org.demo.latte.ui.recycle.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by zhanyi on 2017/11/29.
 */

public class IndexDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);

            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");

            Log.i("imageUrl", imageUrl);
            Log.i("text", text);
            Log.i("spanSize", spanSize + "");
            Log.i("goodsId", id + "");


            Log.i("banners is", banners == null ? "null" : "not null");

            final ArrayList<String> bannerImages = new ArrayList<>();

            int type = 0;

            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.TEXT_IMAGE;
            }

            if (banners != null) {
                type = ItemType.BANNER;
                //banner初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                    Log.i("banner", banner + "");
                }
            }

            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(type)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, text)
                    .setField(MultipleFields.IMAGE_URL, imageUrl)
                    .setField(MultipleFields.BANNERS, bannerImages)
                    .build();

            ENTITIES.add(entity);

        }

        return ENTITIES;
    }


}
