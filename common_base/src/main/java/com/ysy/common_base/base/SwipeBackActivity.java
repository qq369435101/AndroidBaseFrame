package com.ysy.common_base.base;


import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ysy.common_base.R;
import com.ysy.common_base.databinding.ActivityBaseBinding;
import com.ysy.common_base.listener.PerfectClickListener;


/**
 * Created by liufei on 2017/12/18.
 */

public abstract class SwipeBackActivity<SV extends ViewDataBinding> extends BaseActivity {
    protected SV bindingView;
    private ActivityBaseBinding mBaseBinding;
    private View refresh;
    private View loadingView;
    private View emptyView;
    private AnimationDrawable mAnimationDrawable;
    public Activity mActivity;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base, null, false);
        bindingView = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);
        // content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        mBaseBinding.container.addView(bindingView.getRoot());
        getWindow().setContentView(mBaseBinding.getRoot());

        // 设置透明状态栏，兼容4.4
        loadingView = ((ViewStub) findViewById(R.id.vs_loading)).inflate();
        refresh = getView(R.id.ll_error_refresh);
        emptyView = getView(R.id.ll_empty_refresh);
        ImageView img = loadingView.findViewById(R.id.img_progress);

        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }

        // 点击加载失败布局
        refresh.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showLoading();
                onRefresh();
            }
        });
        emptyView.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showLoading();
                onRefresh();
            }
        });
        bindingView.getRoot().setVisibility(View.GONE);
//        changeStateBar();
        QMUIStatusBarHelper.translucent(this, getResources().getColor(R.color.lib_fontBlack));
        changeStatusTextBarColor(false);
        initView();
//        QMUIStatusBarHelper.translucent();
        mActivity = this;
    }

    public void initView() {
    }
    public void initTopBar(String title) {
        if (!mBaseBinding.stubTop.isInflated())
            mBaseBinding.stubTop.getViewStub().inflate();
        QMUITopBarLayout bar = mBaseBinding.getRoot().findViewById(R.id.topbar);
        bar.setBackgroundColor(getResources().getColor(R.color.app_theme));
        bar.addLeftBackImageButton().setOnClickListener(v -> finish());
        bar.setTitle(title);
    }

    public QMUITopBarLayout getTopBar() {
        if (!mBaseBinding.stubTop.isInflated())
            mBaseBinding.stubTop.getViewStub().inflate();
        return mBaseBinding.getRoot().findViewById(R.id.topbar);
    }

    /**
     * 修改状态栏字体颜色
     *
     * @param isLight
     */
    public void changeStatusTextBarColor(boolean isLight) {
        if (isLight)
            QMUIStatusBarHelper.setStatusBarDarkMode(this);

        else
            QMUIStatusBarHelper.setStatusBarLightMode(this);
    }

    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }


    protected boolean isSwipeBackEnable() {
        return true;
    }

    protected boolean isChangeStateBar() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this).setSwipeEdgePercent((float) 0.25);
        changeBackMode();

    }


    public void changeBackMode() {
        if (isSwipeBackEnable() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SwipeBackHelper.getCurrentPage(this)
                    .setSwipeBackEnable(true)
                    .setSwipeSensitivity(0.5f)
                    .setSwipeRelateEnable(true)
                    .setSwipeSensitivity(1);
        } else {
            SwipeBackHelper.getCurrentPage(this)
                    .setSwipeBackEnable(false);
        }
    }

    public void changeStateBar() {
        //适配凹口屏
        if (isChangeStateBar()) {
            int result = 0;
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = getResources().getDimensionPixelSize(resourceId);
                result = result * 2 / 3;
            }
            mBaseBinding.getRoot().setPadding(0, result, 0, 0);
        }
    }

    public void changeStateBar(double ratio) {
        //适配凹口屏
        if (isChangeStateBar()) {
            int result = 0;
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = getResources().getDimensionPixelSize(resourceId);
                result = (int) (result * ratio);
            }
            mBaseBinding.getRoot().setPadding(0, result, 0, 0);
        }
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

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        SwipeBackHelper.onDestroy(this);
        super.onDestroy();
    }

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
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
        if (emptyView.getVisibility() != View.GONE) {
            emptyView.setVisibility(View.GONE);
        }
    }

    protected void showContentView() {
        if (loadingView != null && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
        if (emptyView.getVisibility() != View.GONE) {
            emptyView.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }

    /**
     * 页面错误时，显示的画面
     */
    protected void showError() {
        if (loadingView != null && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refresh.getVisibility() != View.VISIBLE) {
            refresh.setVisibility(View.VISIBLE);
        }
        if (emptyView.getVisibility() != View.GONE) {
            emptyView.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

    /**
     * 页面内容为空时显示的画面
     */
    protected void showEmpty() {
        if (loadingView != null && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (emptyView.getVisibility() != View.VISIBLE) {
            emptyView.setVisibility(View.VISIBLE);
        }
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

    /**
     * 失败后点击刷新
     */
    protected void onRefresh() {

    }


}
