package com.ysy.common_base.rx;

import com.ysy.common_base.base.TResponse;
import com.ysy.common_base.event.Event;
import com.ysy.common_base.exception.TaskException;


import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ysy on 2018/1/2.
 */

public class RxHelper {

    private CompositeDisposable mTaskDisposable;
    private CompositeDisposable mEventDisposable;

    public RxHelper() {
    }

    public <T extends TResponse> Disposable execute(Observable<T> observable,
                                                    final Consumer<T> Next,
                                                    final Consumer<Throwable> onError) {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
        Disposable disposable = observable
                .flatMap(t -> {
                    if (t.getCode() == 200 || t.getCode() == 1 || t.getCode() == 2 || t.getCode() == -1) {
                        return Observable.just(t);
                    } else {
                        TaskException taskException = new TaskException(t.getCode(), t.getMsg());
                        taskException.printStackTrace();
                        return Observable.error(taskException);
                    }
                })
                .compose(new RxTransformer<T, T>())
//                .onErrorReturnItem((T) new TResponse())
                .subscribe(Next, onError);
        addTaskDisposable(disposable);
        return disposable;
    }

    public <T> Disposable execute(Observable<T> observable,
                                  final Consumer<T> Next
    ) {
        Disposable disposable = observable
                .compose(new RxTransformer<T, T>())
                .subscribe(Next, throwable -> {
//                    ToastUtils.showToast(throwable.getMessage());
                });
        addTaskDisposable(disposable);
        return disposable;
    }
//
//    public <T> Disposable execute(Observable<TResponse<T>> observable,
//                                  final Consumer<? super T> onNext,
//                                  final Consumer<Throwable> onError) {
//        Disposable disposable = observable
//                .flatMap(response -> {
//                    response.setDeltaTime();
//                    if (response.isSuccess()) {
//                        T data = response.getData();
//                        if (data == null) {
//                            return Observable.error(new NullPointerException("the data of response should not be null"));
//                        } else {
//                            return Observable.just(data);
//                        }
//                    } else {
//                        return Observable.error(new TaskException(response.getErrorCode(), response.getMessage()));
//                    }
//                })
//                .compose(new RxTransformer<T, T>())
//                .subscribe(onNext, onError);
//        addTaskDisposable(disposable);
//        return disposable;
//    }

    public <E extends Event> Disposable observeEvent(
            Class<E> eventClass,
            final Consumer<? super E> onEvent,
            Scheduler scheduler) {
        Disposable disposable = RxBus.getDefault().observeEvents(eventClass)
                .observeOn(scheduler).subscribe(onEvent);
        addEventDisposable(disposable);
        return disposable;
    }

    private void addTaskDisposable(Disposable disposable) {
        if (mTaskDisposable == null) {
            mTaskDisposable = new CompositeDisposable();
        }
        mTaskDisposable.add(disposable);
    }

    private void addEventDisposable(Disposable disposable) {
        if (mEventDisposable == null) {
            mEventDisposable = new CompositeDisposable();
        }
        mEventDisposable.add(disposable);
    }

    public void disposeTask() {
        if (mTaskDisposable != null) {
            mTaskDisposable.dispose();
            mTaskDisposable = null;
        }
    }

    public void disposeEvent() {
        if (mEventDisposable != null) {
            mEventDisposable.dispose();
            mEventDisposable = null;
        }
    }

    /**
     * 统一线程处理
     * <p>
     * 发布事件io线程，接收事件主线程
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {//compose处理线程

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 生成Flowable
     *
     * @param t
     * @return Flowable
     */
    public static <T> Flowable<T> createFlowable(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * 生成Observable
     *
     * @param t
     * @return Flowable
     */
    public static <T> Observable<T> createObservable(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }

}
