package com.ysy.module_user.ui;

import android.os.Bundle;
import android.widget.Button;

import com.billy.cc.core.component.CC;
import com.ysy.common_base.base.SwipeBackActivity;
import com.ysy.common_lib.ActionConstants;
import com.ysy.common_lib.ComponentConstants;
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
        bindingView.includeTopBar.topbar.addLeftBackImageButton().setOnClickListener(v -> finish());
        bindingView.includeTopBar.topbar.setTitle(getResources().getString(R.string.user_login)).setTextColor(getResources().getColor(R.color.lib_white));
        bindingView.includeTopBar.topbar.setBackgroundColor(getResources().getColor(R.color.lib_transparent));
        bindingView.includeTopBar.topbar.addLeftBackImageButton().setOnClickListener(v -> finish());
        Button register = bindingView.includeTopBar.topbar.addRightTextButton(getResources().getString(R.string.user_register), R.id.topbar_right_text_button);
        register.setTextColor(getResources().getColor(R.color.lib_white));
        register.setOnClickListener(v -> CC.obtainBuilder(ComponentConstants.ComponentUser)
                .setActionName(ActionConstants.ActionToRegister)
                .build()
                .call());
    }
}
