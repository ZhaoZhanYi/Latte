package org.demo.latte.ec.main.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;
import android.widget.Button;

import org.demo.latte.app.Latte;
import org.demo.latte.delegates.bottom.BottomItemDelegate;
import org.demo.latte.ec.R;
import org.demo.latte.ec.R2;

import java.net.URL;

import butterknife.BindView;
import retrofit2.http.Url;

/**
 * Created by zzl on 2018/1/7.
 */

public class FindDelegate extends BottomItemDelegate {

    @BindView(R2.id.fl_find_container)
    ContentFrameLayout delegateContainer;

    @BindView(R2.id.bn_load)
    Button loadBn;

    private WebView mWebView;

    private boolean mIsWebViewAvailable;

    private String mUrl = "http://aikanvod.miguvideo.com/video/p/zhuanti.jsp";

    @Override
    public Object setLayout() {
        return R.layout.delegate_find;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        delegateContainer
        if (mWebView != null) {
            mWebView.destroy();
        }
        mWebView = new WebView(getContext());

        mIsWebViewAvailable = true;

        delegateContainer.addView(mWebView);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(mUrl);
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
//        loadRootFragment(R.id.fl_find_container, this);
        loadBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl(mUrl);
            }
        });

    }

    /**
     * 暂停
     */
    @Override
    public void onPause() {
        super.onPause();
        mWebView.onPause();
    }

    /**
     * 运行
     */
    @Override
    public void onResume() {
        mWebView.onResume();
        super.onResume();
    }

    /**
     * 释放View
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }

    /**
     * 释放Fragment
     */
    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    /**
     * 获取WebView
     * @return
     */
    public WebView getWebView() {
        return mIsWebViewAvailable ? mWebView : null;
    }
}
