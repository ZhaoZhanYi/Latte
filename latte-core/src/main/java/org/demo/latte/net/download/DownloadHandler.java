package org.demo.latte.net.download;

import android.os.AsyncTask;

import org.demo.latte.net.RestCreator;
import org.demo.latte.net.callback.IError;
import org.demo.latte.net.callback.IFailure;
import org.demo.latte.net.callback.IRequest;
import org.demo.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhanyi on 2017/10/7.
 */

public class DownloadHandler {


    private final String URL;

    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    private final IRequest REQUEST;

    private final String DOWNLOAD_DIR;

    private final String EXTENSION;

    private final String NAME;

    private final ISuccess SUCCESS;

    private final IFailure FAILURE;

    private final IError ERROR;

    public DownloadHandler(String URL,
                           IRequest REQUEST,
                           String DOWNLOAD_DIR,
                           String EXTENSION,
                           String NAME,
                           ISuccess SUCCESS,
                           IFailure FAILURE,
                           IError ERROR) {

        this.URL = URL;
        this.REQUEST = REQUEST;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.ERROR = ERROR;
    }

    public final void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final ResponseBody responseBody = response.body();
                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, responseBody, NAME);

                            //一定要判断task是否结束，否则文件下载可能不全
                            if (task.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            if (ERROR != null) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                        }

                    }
                });
    }
}
