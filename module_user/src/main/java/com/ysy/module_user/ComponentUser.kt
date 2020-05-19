package com.ysy.module_user

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.CCUtil
import com.billy.cc.core.component.IComponent
import com.ysy.common_lib.ActionConstants
import com.ysy.common_lib.ComponentConstants
import com.ysy.module_user.ui.LoginActivity
import com.ysy.module_user.ui.RegisterActivity

/**
 * Created by YuShengyang on 2020/1/20
 * Email       ：18210490506@163.com
 * Description ：
 */
class ComponentUser : IComponent {
    override fun onCall(cc: CC): Boolean {
        when (cc.actionName) {
            ActionConstants.ActionToLogin -> openLoginActivity(cc)
            ActionConstants.ActionToRegister -> openRegisterActivity(cc)
            //确保每个逻辑分支上都会调用CC.sendCCResult将结果发送给调用方
            else -> CC.sendCCResult(cc.callId, CCResult.errorUnsupportedActionName())
        }
        return false
    }

    override fun getName(): String {
        return ComponentConstants.ComponentUser
    }


    private fun openLoginActivity(cc: CC) {
        CCUtil.navigateTo(cc, LoginActivity::class.java)
        CC.sendCCResult(cc.callId, CCResult.success())
    }

    private fun openRegisterActivity(cc: CC) {
        CCUtil.navigateTo(cc, RegisterActivity::class.java)
        CC.sendCCResult(cc.callId, CCResult.success())
    }
}
