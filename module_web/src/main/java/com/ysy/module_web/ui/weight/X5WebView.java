package com.ysy.module_web.ui.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import static android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;

/**
 * Created by YuShengyang on 2020/1/19
 * Email       ：18210490506@163.com
 * Description ：
 */
public class X5WebView extends WebView {

    public X5WebView(Context arg0) {
        super(arg0);
        setBackgroundColor(85621);
        initWebViewSettings(this);
        this.getView().setClickable(true);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
        initWebViewSettings(this);
        this.getView().setClickable(true);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void initWebViewSettings(X5WebView webView) {
        WebSettings webSetting = webView.getSettings();
        //设置WebView是否允许执行JavaScript脚本，默认false，不允许
        webSetting.setJavaScriptEnabled(true);
        //设置WebView加载页面文本内容的编码，默认“UTF-8”
        webSetting.setDefaultTextEncodingName("utf-8");
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置在WebView内部是否允许访问文件，默认允许访问
        webSetting.setAllowFileAccess(true);
        //设置WebView底层的布局算法
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //设置WebView是否支持使用屏幕控件或手势进行缩放，默认是true，支持缩放
        webSetting.setSupportZoom(true);
        webSetting.setTextZoom(100);
        //设置WebView是否使用其内置的变焦机制，该机制集合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
        webSetting.setBuiltInZoomControls(true);
        //设置WebView是否使用viewport，当该属性被设置为false时，加载页面的宽度总是适应WebView控件宽度；当被设置为true，当前页面包含viewport属性标签，在标签中指定宽度值生效，如果页面不包含viewport标签，无法提供一个宽度值，这个时候该方法将被使用
        webSetting.setUseWideViewPort(true);
        //设置WebView是否使用预览模式加载界面。
        webSetting.setLoadWithOverviewMode(true);
        //设置Application缓存API是否开启，默认false
        webSetting.setAppCacheEnabled(true);
        //设置是否开启数据库存储API权限，默认false，未开启
        webSetting.setDatabaseEnabled(true);
        //设置是否开启DOM存储API权限，默认false，未开启，设置为true，WebView能够使用DOM storage API
        webSetting.setDomStorageEnabled(true);
        //设置是否开启定位功能，默认true，开启定位
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //设置WebView是否支持多屏窗口，默认false，不支持。
        webSetting.setSupportMultipleWindows(false);
        setThirdPartyCookiesEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //设置当一个安全站点企图加载来自一个不安全站点资源时WebView的行为
            webSetting.setMixedContentMode(MIXED_CONTENT_ALWAYS_ALLOW);
        }
        setVerticalScrollBarEnabled(false);
    }

    @SuppressLint("NewApi")
    public void setThirdPartyCookiesEnabled(final boolean enabled) {
        if (Build.VERSION.SDK_INT >= 21) {
            //设置WebView访问第三方Cookies策略
            CookieManager.getInstance().setAcceptThirdPartyCookies(this, enabled);
        }
    }

    /**
     * @param object 供JS调用的实例
     * @param name   内部写定为 {@code}
     */
    @SuppressLint("JavascriptInterface")
    @Override
    public void addJavascriptInterface(Object object, String name) {
        super.addJavascriptInterface(object, TextUtils.isEmpty(name) ? "jsInterface" : name);
    }

    @Override
    public void loadUrl(String url) {
        loadUrl(url, true);
    }

    /**
     * 加载页面
     *
     * @param url            源地址
     * @param isAddUserAgent 是否添加自定义UserAgent
     */
    public void loadUrl(String url, boolean isAddUserAgent) {
        if (isAddUserAgent) {
            addUserAgent();
        }
        super.loadUrl(url);
    }

    private void addUserAgent() {
//        String userId = "";
//        if (!TextUtils.isEmpty(UserCache.getInstance().getUserId()))
//            userId = UserCache.getInstance().getUserId();
//        WebSettings settings = this.getSettings();
//        if (!settings.getUserAgentString().contains("pdmiryun")) {
//            Logger.e(settings.getUserAgentString());
//            settings.setUserAgent(settings.getUserAgentString() + " pdmiryun" + " appId/" + AppIdUtils.getAppId() + " userId/" + userId + " currentSiteId/" + AppThemeInstance.getInstance().getSiteId());
//        }
    }
}