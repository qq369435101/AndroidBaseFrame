package com.ysy.common_base.adapter.holder;


import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by yushengyang.
 * Date: 2018/8/23.
 */

public class DataBindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private T bindview;

    public DataBindingViewHolder(@NonNull T bindview) {
        super(bindview.getRoot());
        this.bindview = bindview;
    }


    public T getBindview() {
        return bindview;
    }
}
