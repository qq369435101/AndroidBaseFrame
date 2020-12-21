package com.ysy.common_base.base

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.ysy.common_base.R
import com.ysy.common_base.utils.DevicesUtils
import com.ysy.common_base.utils.WindowUtils

/**
 * author : YuShengyang
 * date   ：2020/12/4
 * desc   ：
 */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setOrientation();
    }

    /**
     * 保持屏幕竖屏锁定（不需要可以重新该方法）
     */
    open fun setOrientation() {
        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    }

    fun initWindow(activity: Activity?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            WindowUtils.setStatusBar(activity, false, false)
        } else {
            DevicesUtils.setWindowStatusBarColor(activity, R.color.lib_colorWhite)
        }
        WindowUtils.setStatusTextColor(true, activity)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.activity_out_left_to_right)
    }
}