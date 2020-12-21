package com.ysy.common_base.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import java.lang.reflect.ParameterizedType;

/**
 * Created by YuShengyang on 2020/1/16
 * Email       ：18210490506@163.com
 * Description ：MVVMActivity
 */
public class MVVMActivity<SV extends ViewDataBinding, VM extends ViewModel> extends SwipeBackActivity<SV> {
    protected VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO:第二个泛型为viewModel若改变泛型位置viewModel会初始化失败
        mViewModel = ViewModelProviders.of(this).get((Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    public VM getViewModel() {
        return mViewModel;
    }
}
