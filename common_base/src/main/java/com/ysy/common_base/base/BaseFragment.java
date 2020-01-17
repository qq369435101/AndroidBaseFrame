package com.ysy.common_base.base;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.ysy.common_base.R;
import com.ysy.common_base.listener.PerfectClickListener;

/**
 * Created by YuShengyang on 2020/1/15
 * Email ：18210490506@163.com
 */
public abstract class BaseFragment<SV extends ViewDataBinding> extends Fragment {

    // 布局view
    protected SV bindingView;
    // fragment是否显示了
    protected boolean mIsVisible = false;
    // 加载中
    private View loadingView;
    // 加载失败
    private LinearLayout mRefresh;
    // 内容布局
    protected RelativeLayout mContainer;
    // 动画
    private AnimationDrawable mAnimationDrawable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ll = inflater.inflate(R.layout.fragment_base, null);
        bindingView = DataBindingUtil.inflate(getActivity().getLayoutInflater(), setContent(), null, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        mContainer = ll.findViewById(R.id.container);
        mContainer.addView(bindingView.getRoot());

        return ll;
    }

    public int getStateBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
            result = result * 2 / 3;
        }
        return result;
    }

    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i("onHiddenChanged", "onHiddenChanged:" + hidden);
        if (!hidden) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean hidden) {
        super.setUserVisibleHint(hidden);
        Log.i("setUserVisibleHint", "onHiddenChanged:" + hidden);
        if (!hidden) {
            mIsVisible = false;
            onInvisible();
        } else {
            mIsVisible = true;
            onVisible();
        }
    }

    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }


    protected void onInvisible() {
    }

    /**
     * 显示时加载数据,需要这样的使用
     * 注意声明 isPrepared，先初始化
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     */
    protected void loadData() {
    }

    protected void onVisible() {
        loadData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadingView = ((ViewStub) getView(R.id.vs_loading)).inflate();
        ImageView img = loadingView.findViewById(R.id.img_progress);

        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        mRefresh = getView(R.id.ll_error_refresh);
        // 点击加载失败布局
        mRefresh.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showLoading();
                onRefresh();
            }
        });
        bindingView.getRoot().setVisibility(View.GONE);

    }

    protected <T extends View> T getView(int id) {
        return (T) getView().findViewById(id);
    }

    /**
     * 布局
     */
    public abstract int setContent();

    /**
     * 加载失败后点击后的操作
     */
    protected void onRefresh() {

    }

    /**
     * 显示加载中状态
     */
    protected void showLoading() {
        if (loadingView != null && loadingView.getVisibility() != View.VISIBLE) {
            loadingView.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
    }

    /**
     * 加载完成的状态
     */
    protected void showContentView() {
        if (loadingView != null && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefresh != null && mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }

    /**
     * 加载失败点击重新加载的状态
     */
    protected void showError() {
        if (loadingView != null && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefresh.getVisibility() != View.VISIBLE) {
            mRefresh.setVisibility(View.VISIBLE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

}