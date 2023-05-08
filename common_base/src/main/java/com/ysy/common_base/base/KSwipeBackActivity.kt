package com.ysy.common_base.base

import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.ColorRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.jude.swipbackhelper.SwipeBackHelper
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.qmuiteam.qmui.widget.QMUITopBar
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.ysy.common_base.R
import com.ysy.common_base.databinding.ActivityBaseBinding
import com.ysy.common_base.listener.PerfectClickListener

/**
 * author : YuShengyang
 * date   ：2020/12/4
 * desc   ：
 */
open class KSwipeBackActivity<SV : ViewDataBinding>() : BaseActivity() {
     var bindingView: SV? = null
    lateinit var mAnimationDrawable: AnimationDrawable
     val mBaseBinding: ActivityBaseBinding by lazy {
         DataBindingUtil.inflate(layoutInflater, R.layout.activity_base, null, false)
     }
    lateinit var loadingView: View
    override fun setContentView(layoutResID: Int) {
        bindingView = DataBindingUtil.inflate(layoutInflater, layoutResID, null, false);
        if (bindingView!=null){
            bindingView?.root?.layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            mBaseBinding.container.addView(bindingView?.root)
            window.setContentView(mBaseBinding.root)
            loadingView = mBaseBinding.vsLoading.viewStub!!.inflate()
            mAnimationDrawable = loadingView.findViewById<ImageView>(R.id.img_progress).drawable as AnimationDrawable
            // 默认进入页面就开启动画
            if (!mAnimationDrawable.isRunning) {
                mAnimationDrawable.start()
            }
            // 点击加载失败布局
            mBaseBinding.llErrorRefresh.setOnClickListener(object : PerfectClickListener() {
                override fun onNoDoubleClick(v: View?) {
                    showLoading()
                    onRefresh()
                }
            })
            mBaseBinding.llEmptyRefresh.setOnClickListener(object : PerfectClickListener() {
                override fun onNoDoubleClick(v: View?) {
                    showLoading()
                    onRefresh()
                }
            })
            bindingView?.root?.visibility = View.GONE
            QMUIStatusBarHelper.translucent(this, getResColor(R.color.lib_fontBlack))
            initView()
        }
    }

    open fun initView() {
    }


    open fun getResColor(@ColorRes id: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getColor(id)
        } else {
            resources.getColor(id)
        }
    }

    open fun initTopBar(title: String) {
        if (!mBaseBinding.stubTop.isInflated) mBaseBinding.stubTop.viewStub!!.inflate()
        val bar: QMUITopBarLayout = mBaseBinding.root.findViewById(R.id.topbar)
        bar.setBackgroundColor(getResColor(R.color.app_theme))
        bar.addLeftBackImageButton().setOnClickListener { finish() }
        bar.setTitle(title)
    }

    open fun getTopBar(): QMUITopBar {
        if (!mBaseBinding.stubTop.isInflated) mBaseBinding.stubTop.viewStub!!.inflate()
        return mBaseBinding.root.findViewById(R.id.topbar)
    }


    open fun changeStatusTextBarColor(isLight: Boolean) {
        if (isLight) QMUIStatusBarHelper.setStatusBarDarkMode(this) else QMUIStatusBarHelper.setStatusBarLightMode(this)
    }

    protected open fun <T : View?> getView(id: Int): T {
        return findViewById<View>(id) as T
    }

    /**
     * 是否支持侧滑退出
     *
     * @return
     */
    protected open fun isSwipeBackEnable(): Boolean {
        return true
    }

    /**
     * 是否适配刘海屏
     *
     * @return
     */
    protected open fun isChangeStateBar(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SwipeBackHelper.onCreate(this)
        SwipeBackHelper.getCurrentPage(this).setSwipeEdgePercent(0.25.toFloat())
        changeBackMode()
    }

    open fun changeBackMode() {
        if (isSwipeBackEnable() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SwipeBackHelper.getCurrentPage(this)
                    .setSwipeBackEnable(true)
                    .setSwipeSensitivity(0.5f)
                    .setSwipeRelateEnable(true)
                    .setSwipeSensitivity(1f)
        } else {
            SwipeBackHelper.getCurrentPage(this)
                    .setSwipeBackEnable(false)
        }
    }

    open fun changeStateBar() {
        //适配凹口屏
        if (isChangeStateBar()) {
            var result = 0
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = resources.getDimensionPixelSize(resourceId)
                result = result * 2 / 3
            }
            mBaseBinding.root.setPadding(0, result, 0, 0)
        }
    }

    open fun changeStateBar(ratio: Float) {
        //适配凹口屏
        if (isChangeStateBar()) {
            var result = 0
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = resources.getDimensionPixelSize(resourceId)
                result = (result * ratio).toInt()
            }
            mBaseBinding.root.setPadding(0, result, 0, 0)
        }
    }

    open fun getStateBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
            result = result * 2 / 3
        }
        return result
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        SwipeBackHelper.onPostCreate(this)
    }

    override fun onDestroy() {
        SwipeBackHelper.onDestroy(this)
        super.onDestroy()
    }

    protected open fun showLoading() {
        if (loadingView != null && loadingView.visibility != View.VISIBLE) {
            loadingView.visibility = View.VISIBLE
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning) {
            mAnimationDrawable.start()
        }
        if (bindingView?.getRoot()?.getVisibility() != View.GONE) {
            bindingView?.getRoot()?.setVisibility(View.GONE)
        }
        if (mBaseBinding.llErrorRefresh.getVisibility() != View.GONE) {
            mBaseBinding.llErrorRefresh.setVisibility(View.GONE)
        }
        if (mBaseBinding.llEmptyRefresh.getVisibility() != View.GONE) {
            mBaseBinding.llEmptyRefresh.setVisibility(View.GONE)
        }
    }

    protected open fun showContentView() {
        if (loadingView != null && loadingView.visibility != View.GONE) {
            loadingView.visibility = View.GONE
        }
        // 停止动画
        if (mAnimationDrawable.isRunning) {
            mAnimationDrawable.stop()
        }
        if (mBaseBinding.llErrorRefresh.getVisibility() != View.GONE) {
            mBaseBinding.llErrorRefresh.setVisibility(View.GONE)
        }
        if (mBaseBinding.llEmptyRefresh.getVisibility() != View.GONE) {
            mBaseBinding.llEmptyRefresh.setVisibility(View.GONE)
        }
        if (bindingView?.getRoot()?.getVisibility() != View.VISIBLE) {
            bindingView?.getRoot()?.setVisibility(View.VISIBLE)
        }
    }

    /**
     * 页面错误时，显示的画面
     */
    protected open fun showError() {
        if (loadingView != null && loadingView.visibility != View.GONE) {
            loadingView.visibility = View.GONE
        }
        // 停止动画
        if (mAnimationDrawable.isRunning) {
            mAnimationDrawable.stop()
        }
        if (mBaseBinding.llErrorRefresh.getVisibility() != View.GONE) {
            mBaseBinding.llErrorRefresh.setVisibility(View.GONE)
        }
        if (mBaseBinding.llEmptyRefresh.getVisibility() != View.GONE) {
            mBaseBinding.llEmptyRefresh.setVisibility(View.GONE)
        }
        if (bindingView?.getRoot()?.getVisibility() != View.GONE) {
            bindingView?.getRoot()?.setVisibility(View.GONE)
        }
    }

    /**
     * 页面内容为空时显示的画面
     */
    protected open fun showEmpty() {
        if (loadingView != null && loadingView.visibility != View.GONE) {
            loadingView.visibility = View.GONE
        }
        // 停止动画
        if (mAnimationDrawable.isRunning) {
            mAnimationDrawable.stop()
        }
        if (mBaseBinding.llErrorRefresh.getVisibility() != View.GONE) {
            mBaseBinding.llErrorRefresh.setVisibility(View.GONE)
        }
        if (mBaseBinding.llEmptyRefresh.getVisibility() != View.GONE) {
            mBaseBinding.llEmptyRefresh.setVisibility(View.GONE)
        }
        if (bindingView?.getRoot()?.getVisibility() != View.GONE) {
            bindingView?.getRoot()?.setVisibility(View.GONE)
        }
    }

    /**
     * 失败后点击刷新
     */
    protected open fun onRefresh() {}
}