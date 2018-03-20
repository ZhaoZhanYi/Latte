package org.demo.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.demo.latte.delegates.LatteDelegate;
import org.demo.latte.ec.R;
import org.demo.latte.ec.R2;
import org.demo.latte.net.RestClient;
import org.demo.latte.net.callback.IError;
import org.demo.latte.net.callback.IFailure;
import org.demo.latte.net.callback.IRequest;
import org.demo.latte.net.callback.ISuccess;

import butterknife.BindView;

/**
 * Created by zzl on 2018/3/5.
 */

public class MenuDelegate extends LatteDelegate {

    private static final String TAG = "MenuDelegate";

    @BindView(R2.id.lv_menu_list)
    ListView mMenuList;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort_menu;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        requestMenu();
    }

    public void requestMenu() {
        RestClient.builder()
                .url("http://127.0.0.1/sort_menu")
                .params("", "")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d(TAG, "request on success : " + response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.d(TAG, "request on failure");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {
                        Log.d(TAG, "request on error : " + code);
                    }
                })
                .request(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .build()
                .get();
    }
}
