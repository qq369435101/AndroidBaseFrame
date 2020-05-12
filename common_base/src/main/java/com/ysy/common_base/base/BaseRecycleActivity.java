package com.ysy.common_base.base;

import android.os.Bundle;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import com.ysy.common_base.R;
import com.ysy.common_base.adapter.CommonDataBindingViewAdapter;
import com.ysy.common_base.databinding.ActivityBaseRecycleBinding;

/**
 * Created by YuShengyang on 2020/5/7
 * Email       ：18210490506@163.com
 * Description ：公用RecycleViewActivity：用于简化写列表页时的工作量
 */
public abstract class BaseRecycleActivity<Bean, VM extends ViewModel, DataBinding extends ViewDataBinding> extends MVVMActivity<ActivityBaseRecycleBinding, VM> {
    //默认一次加载10条
    public int pageSize = 10;
    //从第一页开始
    public int pageNum = 1;

    public CommonDataBindingViewAdapter<Bean, DataBinding> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_recycle);
        initView();
        getData();
    }

    private void initRefresh() {
        bindingView.refresh.setOnRefreshListener(refreshLayout -> onRefresh());
        bindingView.refresh.setOnLoadMoreListener(refreshLayout -> getData());
    }

    public void onRefresh() {
        pageNum = 1;
        bindingView.refresh.setEnableLoadMore(true);
        adapter.clear();
        getData();
    }

    public void initView() {
        adapter = setAdapter();
        bindingView.recycle.setAdapter(adapter);
        initRefresh();
    }

    /**
     * 结束加载时refresh动画
     *
     * @param isSuccess    是否加载成功
     * @param isNoMoreData 是否还有更多数据
     */
    public void finishLoad(boolean isSuccess, boolean isNoMoreData) {
        bindingView.refresh.finishRefresh(isSuccess);
        if (isNoMoreData) {
            bindingView.refresh.finishLoadMoreWithNoMoreData();
        } else {
            bindingView.refresh.finishLoadMore(isSuccess);
        }
    }

    public void finishLoad(boolean isSuccess) {
        finishLoad(isSuccess, false);
    }

    public abstract void getData();

    /**
     * 设置adapter
     *
     * @return
     */
    public abstract CommonDataBindingViewAdapter<Bean, DataBinding> setAdapter();
}
