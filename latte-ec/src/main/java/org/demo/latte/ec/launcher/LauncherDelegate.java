package org.demo.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.demo.latte.app.AccountManager;
import org.demo.latte.app.IUserChecker;
import org.demo.latte.delegates.LatteDelegate;
import org.demo.latte.ec.R;
import org.demo.latte.ec.R2;
import org.demo.latte.ec.sign.SignUpDelegate;
import org.demo.latte.ui.launcher.ILauncherListener;
import org.demo.latte.ui.launcher.OnLauncherFinishTag;
import org.demo.latte.utils.storage.LattePreference;
import org.demo.latte.utils.timer.BaseTimerTask;
import org.demo.latte.utils.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 启动页
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

    private ILauncherListener mILauncherListener;

    private int mCount = 5;

    private Timer mTimer = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        // task 任务， 0 立即执行， 1000 1秒间隔
        mTimer.schedule(task, 0, 1000);
    }

    /**
     * 该页面实现 ITimerListener 接口，为执行的任务内容
     */
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
                        Log.d("onTimer", mCount + "");
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            Toast.makeText(getContext(), "跳转", Toast.LENGTH_SHORT).show();
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
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
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    Log.d("checkAccount", "onSignIn");
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    Log.d("checkAccount", "onNotSignIn");
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }

                }
            });

        }
    }


}
