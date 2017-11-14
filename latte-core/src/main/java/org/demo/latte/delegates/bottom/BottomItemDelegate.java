package org.demo.latte.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import org.demo.latte.R;
import org.demo.latte.delegates.LatteDelegate;

/**
 * Created by zhanyi on 2017/11/14.
 */

public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {

    // 退出时间
    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        // 获取fragment onCreateView 加载的 layout
        final View rootView = getView();
        if (rootView != null) {
            // 重新获取焦点，如果不获取焦点可能双击返回有问题
            // TODO 了解获取焦点的作用？？？？？
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }
    /**
     * 监听按键事件
     * @param v
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        // 是返回按键 && 按下事件
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - mExitTime) > EXIT_TIME) {
                Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
                mExitTime = currentTime;
            } else {
                // 本app为单activity架构，只要退出当前activity就完成退出app了
                _mActivity.finish();
            }
            return true;
        }
        return false;
    }
}
