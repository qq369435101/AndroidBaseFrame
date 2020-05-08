package com.ysy.common_base.binding;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by yushengyang.
 * Date: 2018/9/19.
 */

public class BindingView {

    @BindingAdapter({"android:setVisibility"})
    public static void setVisibility(View view, String content) {
        if (content == null || content.equals("")) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }


    @BindingAdapter({"android:isCollect"})
    public static void isCollect(CheckBox box, String content) {
        if (content != null) {
            if (!content.equals("0")) {
                box.setChecked(true);
            } else {
                box.setChecked(false);
            }
        }
    }


    @BindingAdapter({"android:setListVisibility"})
    public static void setVisibility(View view, int size) {
        if (size == 0) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }


    @BindingAdapter("android:setWeightPercent")
    public static void setWeightPercent(View view, int weight) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.weight = weight;
        view.setLayoutParams(layoutParams);
    }


    @BindingAdapter({"android:RecRowCount"})
    public static void RecRowCount(RecyclerView recycleView, int RowCount) {
        if (RowCount == 1) {
            recycleView.setLayoutManager(new LinearLayoutManager(recycleView.getContext()));
        } else if (RowCount == -1) {
            recycleView.setLayoutManager(new LinearLayoutManager(recycleView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        } else {
            recycleView.setLayoutManager(new GridLayoutManager(recycleView.getContext(), RowCount));
        }
    }

    @BindingAdapter({"android:RecDivisionType"})
    public static void RecDivisionType(RecyclerView recycleView, int divisionType) {
        if (divisionType == 1) {
            recycleView.addItemDecoration(new DividerItemDecoration(recycleView.getContext(), DividerItemDecoration.VERTICAL));
        }
    }


}
