package com.ysy.androidbaseframe;


import android.os.Bundle;
import android.view.View;

import com.android.baseframe.R;
import com.billy.cc.core.component.CC;


import com.android.baseframe.databinding.ActivityMainBinding;
import com.ysy.common_base.base.SwipeBackActivity;
import com.ysy.common_lib.ComponentConstants;

public class MainActivity extends SwipeBackActivity<ActivityMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showContentView();
        bindingView.toMainComponent.setOnClickListener(v -> CC.obtainBuilder(ComponentConstants.ComponentMain)
                .setActionName("toMain")
                .build()
                .call());
    }
}
