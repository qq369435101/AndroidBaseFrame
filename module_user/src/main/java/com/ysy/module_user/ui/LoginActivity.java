package com.ysy.module_user.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
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
        bindingView.include.topbar.addLeftBackImageButton().setOnClickListener(v -> finish());
        bindingView.include.topbar.setTitle("用户登录").setTextColor(getResources().getColor(R.color.lib_white));
        bindingView.include.topbar.setBackgroundColor(Color.parseColor("#00ffffff"));
        bindingView.include.topbar.addLeftBackImageButton().setOnClickListener(v -> finish());
        Button register = bindingView.include.topbar.addRightTextButton("注册", R.id.topbar_right_text_button);
        register.setTextColor(getResources().getColor(R.color.lib_white));
        register.setOnClickListener(v -> ToastUtil.showToast(mActivity, "注册"));
    }
}
