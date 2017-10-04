package org.demo.latte.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhanyi on 2017/10/4.
 */

public class RequestCallbacks implements Callback<String> {
    private final IRequest iRequest;

    private final ISuccess iSuccess;

    private final IFailure iFailure;

    private final IError iError;

    public RequestCallbacks(IRequest iRequest, ISuccess iSuccess, IFailure iFailure, IError iError) {
        this.iRequest = iRequest;
        this.iSuccess = iSuccess;
        this.iFailure = iFailure;
        this.iError = iError;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (iSuccess != null) {
                    iSuccess.onSuccess(response.body());
                }
            }
        } else {
            if (iError != null) {
                iError.onError(response.code(), response.message());
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable throwable) {
        if (iFailure != null) {
            iFailure.onFailure();
        }

        if (iRequest != null) {
            iRequest.onRequestEnd();
        }
    }
}
