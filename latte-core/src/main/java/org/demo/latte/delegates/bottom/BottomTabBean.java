package org.demo.latte.delegates.bottom;

/**
 * 底部导航栏 bean
 * Created by zhanyi on 2017/11/14.
 */

public class BottomTabBean {
    // 变量为final
    private final CharSequence ICON;
    private final CharSequence TITLE;

    // 构造方法内一次赋值成型
    // TODO: 2017/11/14 并发编程有好处？
    public BottomTabBean(CharSequence ICON, CharSequence TITLE) {
        this.ICON = ICON;
        this.TITLE = TITLE;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
