package com.ysy.module_user.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ysy.common_base.base.MVVMActivity;
import com.ysy.module_user.R;
import com.ysy.module_user.data.UserInfoViewModel;
import com.ysy.module_user.databinding.ActivityUserInfoBinding;

public class UserInfoActivity extends MVVMActivity<ActivityUserInfoBinding, UserInfoViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
    }
}
