package org.demo.latte.net.rx;

import android.content.Context;

import org.demo.latte.net.RestClient;
import org.demo.latte.net.RestCreator;
import org.demo.latte.net.callback.IError;
import org.demo.latte.net.callback.IFailure;
import org.demo.latte.net.callback.IRequest;
import org.demo.latte.net.callback.ISuccess;
import org.demo.latte.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by zhanyi on 2017/10/4.
 */

public class RxRestClientBuilder {

    private String mUrl;

    private final WeakHashMap<String, Object> mParams = RestCreator.getParams();

    private RequestBody mBody;

    private File mFile;

    private Context mContext;

    private LoaderStyle mLoaderStyle;

    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        this.mParams.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        this.mParams.put(key, value);
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public RxRestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    public RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClient build() {
        return new RxRestClient(
                mUrl,
                mParams,
                mBody,
                mFile,
                mContext,
                mLoaderStyle
        );
    }

}
