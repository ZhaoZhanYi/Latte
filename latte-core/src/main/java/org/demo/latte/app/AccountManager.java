package org.demo.latte.app;

import org.demo.latte.utils.storage.LattePreference;

/**
 * Created by zhanyi on 2017/10/28.
 */

public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    /**
     * 保存登录状态，登录后调用
     * @param state
     */
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    /**
     * 处理启动时登录和非登录的流程
     * @param checker
     */
    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }

    /**
     * 判断是否已经登录
     * @return
     */
    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }
}
