package com.ysy.common_base.network;


import androidx.lifecycle.ViewModel;

import java.lang.reflect.ParameterizedType;

/**
 * Created by yushengyang.
 * Date: 2019/1/23.
 */

public abstract class BaseViewModel<T extends BaseRepository> extends ViewModel {
    public T repository;

    public BaseViewModel() {
        try {
            //初始化Repository
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
            repository=clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCleared() {
        if (repository != null) {
            repository.onDestroy();
        }
        super.onCleared();
    }
}
