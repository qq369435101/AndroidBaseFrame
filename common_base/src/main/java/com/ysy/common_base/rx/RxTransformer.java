package com.ysy.common_base.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xianglanzuo on 2018/1/2.
 */

public class RxTransformer<Upstream, Downstream> implements ObservableTransformer<Upstream, Downstream> {
    @Override
    public ObservableSource<Downstream> apply(Observable<Upstream> upstream) {
        return (ObservableSource<Downstream>) upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
