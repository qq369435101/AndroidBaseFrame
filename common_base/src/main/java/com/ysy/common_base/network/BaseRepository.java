package com.ysy.common_base.network;



import com.ysy.common_base.base.TResponse;
import com.ysy.common_base.rx.RxHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
/**
 * Created by yushengyang.
 * Date: 2018/9/25.
 */

/**
 * 如果要进行缓存，输入缓存类型
 * @param <T>
 */

public class BaseRepository<T>  {

    public Map<String ,T> dataCache=new HashMap<>();

    private RxHelper mSubscribeHelper;

    private void cancelTask() {
        if (mSubscribeHelper != null) {
            mSubscribeHelper.disposeTask();
        }
    }

    private void cancelEvent() {
        if (mSubscribeHelper != null) {
            mSubscribeHelper.disposeEvent();
        }
    }

    private RxHelper getSubscribeHelper() {
        if (mSubscribeHelper == null) {
            mSubscribeHelper = new RxHelper();
        }

        return mSubscribeHelper;
    }
    public <T extends TResponse> void sendRequest(Observable<T> observable, Consumer<T> onSuccess, Consumer<Throwable> onFailure) {
        getSubscribeHelper().execute(observable, onSuccess, onFailure == null ? this::defaultFailure : onFailure);
    }
    public <T> void sendRequest(Observable<T> observable, Consumer<T> onSuccess) {
        getSubscribeHelper().execute(observable, onSuccess);
    }
    private void defaultFailure(Throwable throwable) {
        // TODO: 2018/1/2
    }

    public void onDestroy() {
        cancelTask();
        cancelEvent();
    }


}
