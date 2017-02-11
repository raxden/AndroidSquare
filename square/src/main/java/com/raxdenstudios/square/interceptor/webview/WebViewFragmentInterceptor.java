package com.raxdenstudios.square.interceptor.webview;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ScrollView;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.interceptor.FragmentInterceptor;

/**
 * Created by agomez on 16/07/2015.
 */
public class WebViewFragmentInterceptor
        extends FragmentInterceptor<WebViewInteractor, WebViewInterceptorCallback>
        implements WebViewInteractor {

    private ViewGroup mContainer;
    private WebView mWebView;
    private boolean mPageFinished;

    public WebViewFragmentInterceptor(@NonNull Fragment fragment, @NonNull WebViewInterceptorCallback callback) {
        super(fragment, callback);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContainer = mCallback != null ? mCallback.onLoadWebViewContainer() : null;
        if (mContainer != null) {
            mWebView = new WebView(mContext);
            mWebView.setId(R.id.app__webview);

            mContainer.removeAllViews();
            mContainer.addView(mWebView);

            mWebView.setScrollBarStyle(ScrollView.SCROLLBARS_INSIDE_OVERLAY);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            mWebView.getSettings().setAllowFileAccess(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
            }
            mWebView.getSettings().setBuiltInZoomControls(false);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                mWebView.getSettings().setTextZoom(80);
            }

            CookieManager.getInstance().setAcceptCookie(true);

            mCallback.onConfigureWebSettings(mWebView.getSettings());

            mWebView.setWebViewClient(webviewClient);
            mWebView.setWebChromeClient(webChromeClient);

            mCallback.onWebViewCreate(mWebView);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) mWebView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) mWebView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
        if (mContainer != null) mContainer.removeAllViews();
    }

    private void onProgressChanged(WebView view, int newProgress) {
        if (view != null && !mPageFinished) {
            if (mCallback != null) mCallback.onProgressShow(Integer.toString(newProgress) + "%");
        }
    }

    private void onPageFinished(WebView view, String url) {
        if (view != null) {
            if (mCallback != null) mCallback.onProgressHide();
        }
    }

    private WebChromeClient webChromeClient = new WebChromeClient() {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            WebViewFragmentInterceptor.this.onProgressChanged(view, newProgress);
        }
    };

    private WebViewClient webviewClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mPageFinished = false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mPageFinished = true;
            WebViewFragmentInterceptor.this.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }

    };
}
