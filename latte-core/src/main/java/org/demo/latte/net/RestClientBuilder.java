package org.demo.latte.net;

import android.content.Context;

import org.demo.latte.net.callback.IError;
import org.demo.latte.net.callback.IFailure;
import org.demo.latte.net.callback.IRequest;
import org.demo.latte.net.callback.ISuccess;
import org.demo.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by zhanyi on 2017/10/4.
 */

public class RestClientBuilder {

    private String mUrl;

    private final WeakHashMap<String, Object> mParams = RestCreator.getParams();

    private IRequest mIRequest;

    private ISuccess mISuccess;

    private IFailure mIFailure;

    private IError mIError;

    private RequestBody mBody;

    private File mFile;

    private Context mContext;

    private LoaderStyle mLoaderStyle;

    private String mDownloadDir;

    private String mExtension;

    private String mName;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        this.mParams.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        this.mParams.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder request(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder downloadDir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public RestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    public RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClient build() {
        return new RestClient(
                mUrl,
                mParams,
                mDownloadDir,
                mExtension,
                mName,
                mIRequest,
                mISuccess,
                mIFailure,
                mIError,
                mBody,
                mFile,
                mContext,
                mLoaderStyle
        );
    }

}
