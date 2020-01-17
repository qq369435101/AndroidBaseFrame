package com.ysy.common_base.base;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yushengyang.
 * Date: 2018/10/19.
 */

public class BaseEventHandler {
    private int codeTime=60;
    private Disposable disposable;



    /**
     * 根据Class反射打开activity
     * @param view
     * @param clz
     */
    public void clickToActivity(View view, String clz) {
        Intent intent = null;
        try {
            intent = new Intent(view.getContext(), Class.forName(clz));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        view.getContext().startActivity(intent);
    }


    /**
     * 退出activity
     * @param view
     */
    public void finishActivity(View view) {
        ((Activity) view.getContext()).onBackPressed();
    }

    /**
     * 执行倒计时
     * @param view
     * @param listener
     */
    public void codeTime(TextView view, View.OnClickListener listener) {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);
        disposable = interval.take(codeTime).map(aLong -> codeTime - aLong).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            if (view != null) {
                view.setText(aLong + "s");
            }
            if (aLong == 1) {
                view.setOnClickListener(listener);
                view.setText("获取验证码");
            }
        });
    }

    /**
     * 销毁时调用（倒计时）
     */
    public void onDestroy(){
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }
}
