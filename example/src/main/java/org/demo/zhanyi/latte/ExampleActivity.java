package org.demo.zhanyi.latte;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import org.demo.latte.activities.ProxyActivity;
import org.demo.latte.app.Latte;
import org.demo.latte.delegates.LatteDelegate;
import org.demo.latte.ec.launcher.LauncherDelegate;
import org.demo.latte.ec.main.EcBottomDelegate;
import org.demo.latte.ec.sign.ISignListener;
import org.demo.latte.ec.sign.SignInDelegate;
import org.demo.latte.ui.launcher.ILauncherListener;
import org.demo.latte.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        隐藏actionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

//        保存全局的Activity
        Latte.getConfigurator().withActivity(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }


    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户已登录", Toast.LENGTH_LONG).show();
                startWithPop(new EcBottomDelegate());

                break;

            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户未登录了", Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate());
                break;

            default:
                break;
        }
    }
}
