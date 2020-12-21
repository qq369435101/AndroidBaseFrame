package com.ysy.androidbaseframe;


import android.os.Bundle;
import android.view.View;

import com.android.baseframe.R;
import com.android.baseframe.databinding.ActivityMainBinding;
import com.billy.cc.core.component.CC;
import com.ysy.common_base.base.SwipeBackActivity;
import com.ysy.common_lib.ActionConstants;
import com.ysy.common_lib.ComponentConstants;
import com.ysy.common_lib.ParamsConstants;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends SwipeBackActivity<ActivityMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showContentView();
        initTopBar("MainApp");
    }

    public void ToHome(View view) {
        CC.obtainBuilder(ComponentConstants.ComponentMain)
                .setActionName(ActionConstants.ActionToMain)
                .build()
                .call();
    }

    public void ToWeb(View view) {
        Map<String, Object> map = new HashMap<>();
        map.put(ParamsConstants.Web_Url, "https://www.baidu.com");
        map.put(ParamsConstants.Title, "百度一下");
        CC.obtainBuilder(ComponentConstants.ComponentWeb)
                .setActionName(ActionConstants.ActionToWeb)
                .addParams(map)
                .build()
                .call();
    }

    public void toLogin(View view) {
        CC.obtainBuilder(ComponentConstants.ComponentUser)
                .setActionName(ActionConstants.ActionToLogin)
                .build()
                .call();
    }

}
