package com.ysy.common_base.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.ysy.common_base.R;


/**
 * Created by tujh on 2018/6/28.
 */
public final class ToastUtil {
    private static Toast sNormalToast;
    private static Toast sFineToast;
    private static Toast sWhiteTextToast;


    public static void showToast(Context context, String str) {
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToast(Context context, int str) {
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showWhiteTextToast(Context context, @StringRes int strId) {
        showWhiteTextToast(context, context.getString(strId));
    }

    public static void showWhiteTextToast(Context context, String text) {
        if (sWhiteTextToast == null) {
            Resources resources = context.getResources();
            TextView textView = new TextView(context);
            textView.setTextColor(resources.getColor(R.color.lib_white));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.x64));
            textView.setText(text);
            sWhiteTextToast = new Toast(context);
            sWhiteTextToast.setView(textView);
            sWhiteTextToast.setDuration(Toast.LENGTH_SHORT);
            int yOffset = context.getResources().getDimensionPixelSize(R.dimen.x560);
            sWhiteTextToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, yOffset);
            sWhiteTextToast.show();
        } else {
            TextView view = (TextView) sWhiteTextToast.getView();
            view.setText(text);
            if (!view.isShown()) {
                sWhiteTextToast.show();
            }
        }
    }

    public static void showNormalToast(Context context, @StringRes int strId) {
        String text = context.getString(strId);
        if (sNormalToast == null) {
            context = context.getApplicationContext();
            Resources resources = context.getResources();
            TextView textView = new TextView(context);
            textView.setTextColor(resources.getColor(R.color.lib_white));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.x26));
            textView.setBackgroundResource(R.drawable.more_toast_background);
            int hPadding = resources.getDimensionPixelSize(R.dimen.x28);
            int vPadding = resources.getDimensionPixelSize(R.dimen.x16);
            textView.setPadding(hPadding, vPadding, hPadding, vPadding);
            textView.setText(text);
            sNormalToast = new Toast(context);
            sNormalToast.setView(textView);
            sNormalToast.setDuration(Toast.LENGTH_SHORT);
            int yOffset = context.getResources().getDimensionPixelSize(R.dimen.x582);
            sNormalToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, yOffset);
            sNormalToast.show();
        } else {
            TextView textView = (TextView) sNormalToast.getView();
            textView.setText(text);
            if (!textView.isShown()) {
                sNormalToast.show();
            }
        }
    }

    public static Toast makeNormalToast(Context context, @StringRes int strId) {
        return makeNormalToast(context, context.getString(strId));
    }

    public static Toast makeNormalToast(Context context, String text) {
        if (sNormalToast == null) {
            context = context.getApplicationContext();
            Resources resources = context.getResources();
            TextView textView = new TextView(context);
            textView.setTextColor(resources.getColor(R.color.lib_white));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.x26));
            textView.setBackgroundResource(R.drawable.more_toast_background);
            int hPadding = resources.getDimensionPixelSize(R.dimen.x28);
            int vPadding = resources.getDimensionPixelSize(R.dimen.x16);
            textView.setPadding(hPadding, vPadding, hPadding, vPadding);
            textView.setText(text);
            sNormalToast = new Toast(context);
            sNormalToast.setView(textView);
            sNormalToast.setDuration(Toast.LENGTH_SHORT);
        } else {
            ((TextView) sNormalToast.getView()).setText(text);
        }
        int yOffset = context.getResources().getDimensionPixelSize(R.dimen.x582);
        sNormalToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, yOffset);
        return sNormalToast;
    }
}
