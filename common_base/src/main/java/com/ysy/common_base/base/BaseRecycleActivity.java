package com.ysy.common_base.base;

import android.os.Bundle;

import androidx.databinding.ViewDataBinding;

import com.ysy.common_base.R;
import com.ysy.common_base.adapter.CommonDataBindingViewAdapter;
import com.ysy.common_base.databinding.ActivityBaseRecycleBinding;

/**
 * Created by YuShengyang on 2020/5/7
 * Email       ：18210490506@163.com
 * Description ：公用RecycleViewActivity
 */
public abstract class BaseRecycleActivity<Bean extends TResponse, DataBinding extends ViewDataBinding> extends SwipeBackActivity<ActivityBaseRecycleBinding> {
    //默认一次加载10条
    public int pageSize = 10;
    //从第一页开始
    public int pageNum = 1;

    CommonDataBindingViewAdapter<Bean, DataBinding> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_recycle);
        showContentView();
        initRefresh();
        adapter = setAdapter();

    }

    private void initRefresh() {
        bindingView.refresh.setOnRefreshListener(refreshLayout -> onRefresh());
        bindingView.refresh.setOnLoadMoreListener(refreshLayout -> getData());
    }

    public abstract void onRefresh();

    public abstract void getData();

    public abstract CommonDataBindingViewAdapter<Bean, DataBinding> setAdapter();
}
