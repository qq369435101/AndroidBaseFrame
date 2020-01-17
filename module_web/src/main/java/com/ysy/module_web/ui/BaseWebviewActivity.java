package com.ysy.module_web.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ysy.common_base.base.SwipeBackActivity;
import com.ysy.module_web.R;
import com.ysy.module_web.databinding.ActivityWebBaseBinding;

/**
 * Created by YuShengyang on 2020/1/16
 * Email       ：18210490506@163.com
 * Description ：
 */
public class BaseWebviewActivity extends SwipeBackActivity<ActivityWebBaseBinding> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_base);
    }
}