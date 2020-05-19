package com.ysy.module_user.ui

import android.os.Bundle
import com.ysy.common_base.base.MVVMActivity
import com.ysy.module_user.R
import com.ysy.module_user.data.UserInfoViewModel
import com.ysy.module_user.databinding.ActivityFindPasswordBinding

class FindPasswordActivity : MVVMActivity<ActivityFindPasswordBinding, UserInfoViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_password)
        showContentView()
    }

    override fun initView() {
        initTopBar(resources.getString(R.string.user_find_password))
        topBar.addLeftBackImageButton().setOnClickListener { finish() }
    }
}
