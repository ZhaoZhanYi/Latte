package org.demo.latte.ec.sign;

/**
 * Created by zhanyi on 2017/10/28.
 */

public interface ISignListener {
    /**
     * 登录成功回调
     */
    void onSignInSuccess();

    /**
     * 注册成功回调
     */
    void onSignUpSuccess();

}
