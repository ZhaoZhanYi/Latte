package org.demo.latte.wechat;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.demo.latte.app.ConfigType;
import org.demo.latte.app.Latte;
import org.demo.latte.wechat.callbacks.IWeChatSignInCallback;

/**
 * Created by zhanyi on 2017/11/7.
 */

public class LatteWeChat {
    static final String APP_ID = (String) Latte.getConfiguration(ConfigType.WE_CHAT_APP_ID);
    static final String APP_SECRET = (String) Latte.getConfiguration(ConfigType.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;

    private IWeChatSignInCallback mSignInCallback;

    private LatteWeChat() {
        Activity activity = (Activity) Latte.getConfiguration(ConfigType.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    /**
     * 单例懒汉模式
     */
    private static final class Holder {

        private static final LatteWeChat INSTANCE = new LatteWeChat();

    }
    public static LatteWeChat getInstance() {
        return Holder.INSTANCE;
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public LatteWeChat onSignInCallback(IWeChatSignInCallback callback) {
        this.mSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
