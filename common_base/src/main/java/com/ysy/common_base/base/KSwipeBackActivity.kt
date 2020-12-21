package com.ysy.common_base.base

import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlinx.android.synthetic.main.activity_base.*

/**
 * author : YuShengyang
 * date   ：2020/12/4
 * desc   ：
 */
open class KSwipeBackActivity<SV : ViewDataBinding>(sv: SV) : BaseActivity() {
    override fun setContentView(layoutResID: Int) {
        lateinit var dataBinding: SV
        super.setContentView(layoutResID)
        dataBinding = DataBindingUtil.inflate(layoutInflater, layoutResID, null, false);
        dataBinding.root.layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        container.addView(dataBinding.root)
        window.setContentView(base_view)

    }
}