package org.demo.latte.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;

import org.demo.latte.R;
import org.demo.latte.R2;
import org.demo.latte.delegates.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by zhanyi on 2017/11/14.
 */

public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {

    // 底部菜单bean
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();

    // 底部菜单的delegate
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();

    // 底部菜单的item
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    // 表示当前是哪一个 delegate
    private int mCurrentDelegate = 0;

    // 表示第一个展示的 delegate
    private int mIndexDelegate = 0;

    // 默认点击的颜色
    private int mClickedColor = Color.RED;

    // 底部菜单布局
    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar;

    /**
     * 添加底部菜单的item
     * @param builder
     * @return
     */
    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    /**
     * 设置底部菜单初始显示下标
     * @return
     */
    public abstract int setIndexDelegate();

    /**
     * 设置点击的颜色
     * @return
     */
    @ColorInt
    public abstract int setClickedColor();

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化默认显示下标
        mIndexDelegate = setIndexDelegate();
        // 初始化选中颜色
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

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
//            设置每一个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);

            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = TAB_BEANS.get(i);

            // 填充图标
            itemIcon.setText(bean.getIcon());

            // 填充标题
            itemTitle.setText(bean.getTitle());

            // 设置第一次展示的item文字图标高亮
            if (i == mIndexDelegate) {
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }

        }

        final SupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new SupportFragment[size]);

        // TODO: 2017/11/15 了解此方法 了解supportFragment 以及 Fragment
        loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
    }

    @Override
    public void onClick(View v) {
        resetColor();
        final int tag = (int) v.getTag();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemIcon.setTextColor(mClickedColor);
        itemTitle.setTextColor(mClickedColor);

        // 显示选中菜单的delegate，第一个参数是要显示的fragment，第二个参数是要隐藏的fragment
        showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        mCurrentDelegate = tag;
    }

    /**
     * 重置底部菜单的颜色
     */
    private void resetColor() {
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemIcon.setTextColor(Color.GRAY);
            itemTitle.setTextColor(Color.GRAY);
        }
    }
}
