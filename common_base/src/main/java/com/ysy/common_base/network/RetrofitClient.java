package com.ysy.common_base.network;


import com.ysy.common_base.BuildConfig;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ysy on 2018/9/2.
 */

public final class RetrofitClient {

    private static final String BASE_URL;


    static {
        switch (BuildConfig.SERVICE_STATE) {
            case BuildConfig.DEBUG_STATE: {
                BASE_URL = BuildConfig.BASE_URL_DEBUG;
                break;
            }
            case BuildConfig.RELEASE_STATE:
            default:
                BASE_URL = BuildConfig.BASE_URL_RELEASE;
                break;
        }
    }

    private final Retrofit mRetrofit;

    private Map<Class, Object> mServiceMap = new HashMap<>();


    private RetrofitClient() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(HttpClient.getInstance().getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build();
    }

    private static final class RetrofitClientHolder {
        private static final RetrofitClient RETROFIT_CLIENT = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return RetrofitClientHolder.RETROFIT_CLIENT;
    }

    public final <T> T getService(Class<T> tClass) {
        Object service = mServiceMap.get(tClass);
        if (service == null) {
            service = mRetrofit.create(tClass);
            mServiceMap.put(tClass, service);
        }
        return ((T) service);
    }

    public static <T> T createApi(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.baidu.com/")
                .client(HttpClient.getInstance().getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }
}
