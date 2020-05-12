package com.ysy.module_main;

import android.os.Bundle;
import android.view.View;

import com.billy.cc.core.component.CC;
import com.ysy.common_base.base.SwipeBackActivity;
import com.ysy.common_base.weight.BottomSheetUtils;
import com.ysy.common_lib.ActionConstants;
import com.ysy.common_lib.ComponentConstants;
import com.ysy.module_main.databinding.ActivityHomeBinding;


public class HomeActivity extends SwipeBackActivity<ActivityHomeBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        showContentView();
        initTopBar("首页");
        getTopBar().addRightImageButton(R.mipmap.icon_topbar_overflow, R.id.topbar_right_change_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetUtils.showShareDialog(v.getContext());
            }
        });
        bindingView.btnToExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(v.getContext(),ExampleListActivity.class));
                CC.obtainBuilder(ComponentConstants.ComponentMain)
                        .setActionName(ActionConstants.ActionToExampleList)
                        .build()
                        .call();
            }
        });
    }
}
