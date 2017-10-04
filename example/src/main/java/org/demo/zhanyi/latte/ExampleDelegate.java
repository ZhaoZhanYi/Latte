package org.demo.zhanyi.latte;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import org.demo.latte.delegates.LatteDelegate;
import org.demo.latte.net.RestClient;
import org.demo.latte.net.callback.IError;
import org.demo.latte.net.callback.IFailure;
import org.demo.latte.net.callback.ISuccess;

/**
 * Created by feibai on 2017/9/10.
 */

public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testResetClient();
    }

    public void testResetClient() {
        RestClient.builder()
                .url("https://www.baidu.com")
                .params("", "")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {

                    }
                })
                .build()
                .get();
    }
}
