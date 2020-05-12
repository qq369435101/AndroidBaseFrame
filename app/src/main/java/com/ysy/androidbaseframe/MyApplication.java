package com.ysy.androidbaseframe;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.android.baseframe.R;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.ysy.common_base.utils.DynamicTimeFormat;

/**
 * Created by YuShengyang on 2020/5/12
 * Email       ：18210490506@163.com
 * Description ：
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRefrsh();
    }

    private void initRefrsh() {
        //启用矢量图兼容
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer((context, layout) -> {
            //全局设置（优先级最低）
            layout.setEnableLoadMore(false);
            layout.setEnableAutoLoadMore(true);
            layout.setEnableOverScrollDrag(true);
            layout.setEnableOverScrollBounce(true);
            layout.setEnableLoadMoreWhenContentNotFull(true);
            layout.setEnableScrollContentWhenRefreshed(true);
        });
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色（优先级第二低，可以覆盖 DefaultRefreshInitializer 的配置，与下面的ClassicsHeader绑定）
            layout.setPrimaryColorsId(R.color.lib_white, android.R.color.black);
            return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }
}
