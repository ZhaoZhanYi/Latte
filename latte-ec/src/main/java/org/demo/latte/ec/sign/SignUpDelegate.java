package org.demo.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import org.demo.latte.delegates.LatteDelegate;
import org.demo.latte.ec.R;
import org.demo.latte.ec.R2;
import org.demo.latte.net.RestClient;
import org.demo.latte.net.callback.ISuccess;
import org.demo.latte.utils.log.LatteLogger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ZZL on 2017/10/23.
 */

public class SignUpDelegate extends LatteDelegate {
//
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;

    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;

    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;

    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;

    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;

    private ISignListener mISignListener = null;

    /**
     * 登录和注册是App唯一的一个入口，不需要分散到各个delegate中去处理
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }

    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
//            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
            RestClient.builder()
                    .url("http://127.0.0.1/index")
                    .params("name", mName.getText().toString())
                    .params("email", mEmail.getText().toString())
                    .params("phone", mPhone.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
//                            Log.i("response: ", response);
//                            Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                            LatteLogger.json("user_profile", response);
                            SignHandler.onSignUp(response, mISignListener);
                        }
                    })
                    .build()
                    .post();

        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLinkSignIn() {
        start(new SignInDelegate());
    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        Boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        Log.i("SignUpDelegate", "手机号码位数: " + phone.length() + " 手机号码: " + phone);
        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误" + phone.length());
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
