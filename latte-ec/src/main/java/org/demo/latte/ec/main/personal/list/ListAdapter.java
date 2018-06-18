package org.demo.latte.ec.main.personal.list;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;

import org.demo.latte.ec.R;

import java.util.List;

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, ListItemViewHolder> {


    public ListAdapter(List data) {
        super(data);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.item_normal_personal);
        addItemType(ListItemType.ITEM_AVATAR, R.layout.item_normal_personal);
        addItemType(ListItemType.ITEM_SWITCH, R.layout.item_normal_personal);
    }


    @Override
    protected void convert(ListItemViewHolder listItemViewHolder, ListBean listBean) {

        switch (listItemViewHolder.getItemViewType()) {

            case ListItemType.ITEM_NORMAL:
                listItemViewHolder.setText(R.id.tv_title, listBean.getTitle());
                break;

            case ListItemType.ITEM_AVATAR:
                break;

            case ListItemType.ITEM_SWITCH:
                break;
        }

    }
}
