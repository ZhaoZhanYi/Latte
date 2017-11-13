package org.demo.latte.wechat.templates;

import org.demo.latte.activities.ProxyActivity;
import org.demo.latte.delegates.LatteDelegate;
import org.demo.latte.wechat.BaseWxEntryActivity;
import org.demo.latte.wechat.LatteWeChat;

/**
 * Created by zhanyi on 2017/11/6.
 */

public class WXEntryTemplate extends BaseWxEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
