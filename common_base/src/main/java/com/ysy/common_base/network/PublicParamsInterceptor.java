package com.ysy.common_base.network;

import android.content.pm.PackageManager;
import android.text.TextUtils;



import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ysy on 2018/1/2.
 */

public class PublicParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request =
                chain.request()
                        .newBuilder()
//                    .addHeader()
                        .build();

        return chain.proceed(request);
    }
}
