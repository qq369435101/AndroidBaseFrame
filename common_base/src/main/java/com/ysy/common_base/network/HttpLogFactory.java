package com.ysy.common_base.network;

import okhttp3.Interceptor;

/**
 * Created by xianglanzuo on 2017/11/21.
 */

public final class HttpLogFactory {

    public static Interceptor BodyInterceptor(boolean debug) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level level = debug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;
        httpLoggingInterceptor.setLevel(level);
        return httpLoggingInterceptor;
    }

    public static Interceptor stethoInterceptor() {
        try {
            Class<?> stethoClass = Class.forName("com.facebook.stetho.okhttp3.StethoInterceptor");
            return ((Interceptor) stethoClass.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chain -> chain.proceed(chain.request());
    }

}
