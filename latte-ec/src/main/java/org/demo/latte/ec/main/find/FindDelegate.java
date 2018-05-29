package org.demo.latte.ec.main.find;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;
import android.widget.Button;
import android.widget.Toast;

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

    private static final String TAG = "FindDelegate";

    @BindView(R2.id.fl_find_container)
    ContentFrameLayout delegateContainer;

    @BindView(R2.id.bn_load)
    Button loadBn;

    private WebView mWebView;

    private boolean mIsWebViewAvailable;

//    private String mUrl = "http://aikanvod.miguvideo.com/video/p/zhuanti.jsp";
    private String mUrl = "file:///android_asset/test.html";

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

//        loadRootFragment(R.id.fl_find_container, this);
        loadBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl(mUrl);
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                request.getUrl().toString();
                Log.d(TAG + "should1", url);
                Uri uri = Uri.parse(url);
                if (uri.getScheme().equals("js")) {
                    if (uri.getAuthority().equals("webview")) {
                        Log.d(TAG, "js call java");
                    }
                }
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                Log.d(TAG + "should12", url);
                Uri uri = Uri.parse(url);
                if (uri.getScheme().equals("js")) {
                    if (uri.getAuthority().equals("webview")) {
                        Log.d(TAG, "js call java");
                    }
                }
                view.loadUrl(mUrl);
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                Log.d(TAG, "======" + message);
                Uri uri = Uri.parse(url);
                if (uri.getScheme().equals("js")) {
                    if (uri.getAuthority().equals("webview")) {
                        Log.d(TAG, "js call java");
                    }
                }

                return true;
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                Log.d(TAG, "======" + message);
                Uri uri = Uri.parse(url);
                if (uri.getScheme().equals("js")) {
                    if (uri.getAuthority().equals("webview")) {
                        Log.d(TAG, "js call java");
                    }
                }
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });

//        mWebView.addJavascriptInterface(new AndroidtoJs(), "test");
        mWebView.addJavascriptInterface(new JsObject(), "myObj");

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

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

    class JsObject extends Object {
        @JavascriptInterface
        public String toString() {
            return "injectedObject";
        }
        @JavascriptInterface
        public void method1(String msg) {
            Log.d("method1", "==========" + msg);
//            return "injectedObject";
        }
        @JavascriptInterface
        public void wrpJsClick(String jsonStr) {
            Log.d("wrpJsClick", "==========" + jsonStr);
//            return "injectedObject";
        }

    }

    public class AndroidtoJs extends Object {

        // 定义JS需要调用的方法
        // 被JS调用的方法必须加入@JavascriptInterface注解
        @JavascriptInterface
        public void hello(String msg) {
            System.out.println("JS调用了Android的hello方法");
        }
    }
}
