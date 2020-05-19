package com.ysy.module_user.ui

import android.os.Bundle
import com.ysy.common_base.base.MVVMActivity
import com.ysy.module_user.R
import com.ysy.module_user.data.UserInfoViewModel
import com.ysy.module_user.databinding.ActivityRegisterBinding

class RegisterActivity : MVVMActivity<ActivityRegisterBinding, UserInfoViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        showContentView()
    }

    override fun initView() {
        initTopBar(resources.getString(R.string.user_register))
        topBar.addLeftBackImageButton().setOnClickListener { finish() }
    }
}
