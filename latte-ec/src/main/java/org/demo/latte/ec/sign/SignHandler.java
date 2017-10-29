package org.demo.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.demo.latte.app.AccountManager;
import org.demo.latte.ec.database.DatabaseManager;
import org.demo.latte.ec.database.UserProfile;

/**
 * Created by ZZL on 2017/10/26.
 */

public class SignHandler {

    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(userProfile);

        //保存用户状态
        AccountManager.setSignState(true);
        //登录成功回调
        signListener.onSignInSuccess();
    }

    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(userProfile);

        //保存用户状态
        AccountManager.setSignState(true);
        //注册成功回调
        signListener.onSignUpSuccess();
    }
}
