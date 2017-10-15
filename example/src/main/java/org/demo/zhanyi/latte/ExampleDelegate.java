package org.demo.zhanyi.latte;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.demo.latte.delegates.LatteDelegate;
import org.demo.latte.net.RestClient;
import org.demo.latte.net.RestCreator;
import org.demo.latte.net.callback.IError;
import org.demo.latte.net.callback.IFailure;
import org.demo.latte.net.callback.ISuccess;
import org.demo.latte.net.rx.RxRestClient;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

        Button btn_download = (Button) rootView.findViewById(R.id.btn_download);
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                testResetClient();
                onCallRxRestClient();
            }
        });
    }

    //TODO:测试方法
    public void testResetClient() {
        RestClient.builder()
                .url("http://192.168.0.105/index")
                .loader(getContext())
                .params("", "")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.i("onSuccess", response);
                        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.i("onFailure", "");

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {
                        Log.i("onError", "code: " + code + "  message: " + message);

                    }
                })
                .build()
                .get();
    }

    public void onCallRxGet() {
        final String url = "http://127.0.0.1/index";
        final WeakHashMap<String, Object> params = new WeakHashMap<>();
        final Observable<String> observable = RestCreator.getRxRestService().get(url, params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {

            }

            @Override
            public void onNext(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void onCallRxRestClient() {
        final String url = "http://127.0.0.1/index";
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
