package com.monitor.changtian.activity;

import android.annotation.TargetApi;
import android.net.http.SslError;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * webview
 */
@EActivity(R.layout.activity_web_view_info)
public class WebViewInfoActivity extends BaseActvity {

    @ViewById(R.id.webView1)
    WebView mwv;

    @AfterViews
    void init() {

//        initToolbar("资讯详情");
        mwv.loadUrl("https://appr.tc/r/245152211");

        mwv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {

                WebViewInfoActivity.this.runOnUiThread(new Runnable(){
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {
                        request.grant(request.getResources());
                    }// run
                });// MainActivity
            }// onPermissionRequest

        });

        mwv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        initOnClik();
        initWebView();
    }
    private void initOnClik() {


        // WebView........
        mwv.setWebViewClient(new WebViewClient() {
            @Override
            //网页加载完成
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
            }

            // 网页加载失败，重新加载
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub

                mwv.loadUrl("https://appr.tc/r/245152211");
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    private void initWebView() {
        // TODO Auto-generated method stub
        WebSettings webSettings = mwv.getSettings();
        webSettings.setJavaScriptEnabled(true); //支持 js
        //webSettings.setPluginsEnabled(true); //支持插件
        webSettings.setUseWideViewPort(false); //将图片调整到适合 webview 的大小
        webSettings.setSupportZoom(true); //支持缩放
        webSettings.supportMultipleWindows(); //多窗口
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭 webview 中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setNeedInitialFocus(true); //当 webview 调用 requestFocus 时为 webview 设置节点
        webSettings.setBuiltInZoomControls(true); //设置支持缩放
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过 JS 打开新窗口
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清空所有Cookie
        CookieSyncManager.createInstance(MyApplication.getInstance());  //Create a singleton CookieSyncManager within a context
        CookieManager cookieManager = CookieManager.getInstance(); // the singleton CookieManager instance
        cookieManager.removeAllCookie();// Removes all cookies.
        CookieSyncManager.getInstance().sync(); // forces sync manager to sync now
        mwv.setWebChromeClient(null);
        mwv.setWebViewClient(null);
        mwv.getSettings().setJavaScriptEnabled(false);
        mwv.clearCache(true);
    }

}
