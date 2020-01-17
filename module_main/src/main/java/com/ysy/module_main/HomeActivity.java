package com.ysy.module_main;

import android.os.Bundle;
import android.view.View;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.ysy.common_base.base.SwipeBackActivity;
import com.ysy.module_main.databinding.ActivityHomeBinding;


public class HomeActivity extends SwipeBackActivity<ActivityHomeBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        showContentView();
        initTopBar("首页");
    }
}
