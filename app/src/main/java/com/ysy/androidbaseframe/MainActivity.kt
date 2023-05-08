package com.ysy.androidbaseframe

import android.os.Bundle
import android.view.View
import com.android.baseframe.R
import com.android.baseframe.databinding.ActivityMainBinding
import com.billy.cc.core.component.CC
import com.ysy.common_base.base.KSwipeBackActivity
import com.ysy.common_lib.ComponentConstants
import com.ysy.common_lib.ActionConstants
import com.ysy.common_lib.ParamsConstants
import java.util.HashMap

class MainActivity : KSwipeBackActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test)
        showContentView()
        initTopBar("MainApp")
    }

    fun ToHome(view: View?) {
        CC.obtainBuilder(ComponentConstants.ComponentMain)
            .setActionName(ActionConstants.ActionToMain)
            .build()
            .call()
    }

    fun ToWeb(view: View?) {
        val map: MutableMap<String, Any> = HashMap()
        map[ParamsConstants.Web_Url] = "https://www.baidu.com"
        map[ParamsConstants.Title] = "百度一下"
        CC.obtainBuilder(ComponentConstants.ComponentWeb)
            .setActionName(ActionConstants.ActionToWeb)
            .addParams(map)
            .build()
            .call()
    }

    fun toLogin(view: View?) {
        CC.obtainBuilder(ComponentConstants.ComponentUser)
            .setActionName(ActionConstants.ActionToLogin)
            .build()
            .call()
    }
}