package com.ysy.common_base.base;

import android.os.Bundle;

import com.ysy.common_base.R;
import com.ysy.common_base.databinding.ActivityBaseRecycleBinding;

/**
 * Created by YuShengyang on 2020/5/7
 * Email       ：18210490506@163.com
 * Description ：公用RecycleViewActivity
 */
public abstract class BaseRecycleActivity<Bean extends TResponse> extends SwipeBackActivity<ActivityBaseRecycleBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_recycle);
        showContentView();
    }

    public abstract void getData();
}
