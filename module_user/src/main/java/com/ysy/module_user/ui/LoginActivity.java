package com.ysy.module_user.ui;

import android.os.Bundle;
import android.widget.Button;

import com.ysy.common_base.base.SwipeBackActivity;
import com.ysy.common_base.utils.ToastUtil;
import com.ysy.module_user.R;
import com.ysy.module_user.databinding.ActivityLoginBinding;

public class LoginActivity extends SwipeBackActivity<ActivityLoginBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        showContentView();
        initTopBar();
    }


    private void initTopBar() {
        initTopBar("用户登录");
        getTopBar().addLeftBackImageButton().setOnClickListener(v -> finish());
        getTopBar().setTitle("用户登录").setTextColor(getResources().getColor(R.color.lib_white));
        getTopBar().setBackgroundColor(getResources().getColor(R.color.lib_white));
        getTopBar().addLeftBackImageButton().setOnClickListener(v -> finish());
        Button register = getTopBar().addRightTextButton("注册", R.id.topbar_right_text_button);
        register.setTextColor(getResources().getColor(R.color.lib_white));
        register.setOnClickListener(v -> ToastUtil.showToast(mActivity, "注册"));
    }
}
