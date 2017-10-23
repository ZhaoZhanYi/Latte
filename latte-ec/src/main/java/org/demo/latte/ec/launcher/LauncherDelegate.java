package org.demo.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import org.demo.latte.delegates.LatteDelegate;
import org.demo.latte.ec.R;
import org.demo.latte.ec.R2;
import org.demo.latte.ec.sign.SignUpDelegate;
import org.demo.latte.utils.storage.LattePreference;
import org.demo.latte.utils.timer.BaseTimerTask;
import org.demo.latte.utils.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhanyi on 2017/10/16.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private int mCount = 5;

    private Timer mTimer = null;

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    /**
     * 检测是否显示引导页面
     */
    private void checkIsShowScroll() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            //启动一个fragment
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            // 检测用户是否登录App
            start(new SignUpDelegate(), SINGLETASK);
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format(("跳过\n{0}s"), mCount));
                    mCount--;

                    //5 次之后跳转
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
//                            Toast.makeText(getContext(), "跳转", Toast.LENGTH_SHORT).show();
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
