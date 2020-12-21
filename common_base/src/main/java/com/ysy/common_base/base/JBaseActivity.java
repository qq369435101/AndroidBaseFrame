package com.ysy.common_base.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ysy.common_base.R;
import com.ysy.common_base.utils.DevicesUtils;
import com.ysy.common_base.utils.WindowUtils;


/**
 * Created by YuShengyang on 2020/1/15
 * Email ：18210490506@163.com
 */
public class JBaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //锁定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * 沉浸式
     *
     * @param activity
     */
    public void initWindow(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            WindowUtils.setStatusBar(this, false, false);
        } else {
            DevicesUtils.setWindowStatusBarColor(activity, R.color.lib_colorWhite);
        }
        WindowUtils.setStatusTextColor(true, activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 沉浸式
     *
     * @param activity
     * @param black    状态栏字体颜色
     */
    public void initWindow(Activity activity, boolean black) {
//        Utils.setStatusBar(this, false, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            WindowUtils.setStatusBar(this, false, false);
        } else {
            DevicesUtils.setWindowStatusBarColor(activity, R.color.lib_colorWhite);
        }
        WindowUtils.setStatusTextColor(black, activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    public void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @Override
    public void finish() {
        super.finish();
        //设置离开动画
        overridePendingTransition(0, R.anim.activity_out_left_to_right);
    }
}
