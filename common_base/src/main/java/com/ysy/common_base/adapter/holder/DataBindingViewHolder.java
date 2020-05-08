package com.ysy.common_base.adapter.holder;


import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by yushengyang.
 * Date: 2018/8/23.
 */

public class DataBindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private T bindview;

    public DataBindingViewHolder(@NonNull T bindview) {
        super(bindview.getRoot());
        this.bindview = bindview;
        mViews = new SparseArray<>();
    }


    public T getBindview() {
        return bindview;
    }
}
