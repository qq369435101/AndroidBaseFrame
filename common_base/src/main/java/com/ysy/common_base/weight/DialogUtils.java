package com.ysy.common_base.weight;

import android.app.Activity;
import android.content.Context;


import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.lang.ref.WeakReference;

/**
 * Created by YuShengyang on 2020.1.16
 * Email       ：18210490506@163.com
 * Description ：QMUI Dialog工具类
 */

public class DialogUtils {
    public static final long DEFAULT_DURATION = 1500;

    /**
     * @param context 传入applicationContext即可
     */
    public static QMUITipDialog showLoadingDialog(Context context) {
        return showLoadingDialog(context, "加载中...");
    }

    /**
     * @param context
     * @param content 显示文字内容
     */
    public static QMUITipDialog showLoadingDialog(Context context, String content) {
        return new QMUITipDialog.Builder(new WeakReference<>(context).get())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(content)
                .create();
    }


    /**
     * @param activity
     * @param content  显示文字内容
     */
    public static void showSuccessDialog(Activity activity, String content, onDialogCloseListener listener) {
        showDefaultDialog(activity, content, QMUITipDialog.Builder.ICON_TYPE_SUCCESS, listener, DEFAULT_DURATION);
    }

    /**
     * @param activity
     * @param content  显示文字内容
     */
    public static void showDefaultDialog(Activity activity, String content, int Type, onDialogCloseListener listener, long duration) {
        WeakReference<Activity> mActivity = new WeakReference<>(activity);
        QMUITipDialog tipDialog = new QMUITipDialog.Builder(mActivity.get())
                .setIconType(Type)
                .setTipWord(content)
                .create();
        tipDialog.show();
        mActivity.get().getWindow().getDecorView().postDelayed(() -> {
            tipDialog.dismiss();
            if (listener != null) {
                listener.DialogClose();
            }
        }, duration <= 0 ? DEFAULT_DURATION : duration);
    }

    /**
     * 只显示弹窗不处理弹窗结束动作
     *
     * @param activity
     * @param content
     */
    public static void showSuccessDialog(Activity activity, String content) {
        showSuccessDialog(activity, content, null);
    }

    /**
     * 默认成功弹窗
     *
     * @param activity
     */
    public static void showSuccessDialog(Activity activity) {
        showSuccessDialog(activity, "已完成", null);
    }

    /**
     * @param activity
     * @param content  显示文字内容
     */
    public static void showFailedDialog(Activity activity, String content, long durationTime, onDialogCloseListener listener) {
        //根据现实字符自动计算显示时间，来确保用户能看完错误提示
        if (durationTime == -1) {
            if (content.length() < 10) {
                durationTime = 1500;
            } else {
                durationTime = 1500 + (content.length() - 10) * 100;
            }
        }
        showDefaultDialog(activity, content, QMUITipDialog.Builder.ICON_TYPE_FAIL, listener, durationTime);
    }

    public static void showFailedDialog(Activity activity, String content) {
        showFailedDialog(activity, content, -1, null);
    }

    public static void showFailedDialog(Activity activity, String content, onDialogCloseListener listener) {
        showFailedDialog(activity, content, -1, listener);
    }

    public static void showFailedDialog(Activity activity) {
        showFailedDialog(activity, "操作失败");
    }

    //提示关闭监听器
    public interface onDialogCloseListener {
        void DialogClose();
    }
}
