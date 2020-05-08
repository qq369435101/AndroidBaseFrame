package com.ysy.common_base.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.ysy.common_base.R;
import com.ysy.common_base.adapter.holder.DataBindingViewHolder;
import com.ysy.common_base.databinding.ItemEmptyBinding;

import java.util.List;


/**
 * Created by yushengyang.
 * Date: 2018/8/23.
 */

public abstract class CommonDataBindingViewAdapter<T, DB extends ViewDataBinding> extends BaseRecyclerAdapter<T, DataBindingViewHolder<DB>> {
    protected Context mContext;
    protected List<T> mDatas;
    private int mLayoutId;
    private boolean canShowEmpty = false;
    private final int EMPTY = 0;
    private final int NO_EMPTY = 1;
    //    private int emptyLayoutId = -1;
    //固定条目数量
    private int MaxCount = -1;
    private View.OnClickListener EmptyClickListener = null;
    private String emptyText;

    public CommonDataBindingViewAdapter(Context context, List<T> datas, int layoutId) {
        super(context, datas);
        mContext = context;
        mDatas = datas;
        mLayoutId = layoutId;
    }

    @NonNull
    @Override
    public DataBindingViewHolder<DB> onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        // 先inflate数据
//        View itemView = mInflater.inflate(, viewGroup, false);
        // 返回ViewHolder
        DataBindingViewHolder<DB> holder = new DataBindingViewHolder<>(DataBindingUtil.inflate(((Activity) viewGroup.getContext()).getLayoutInflater(), getLayoutId(type), null, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataBindingViewHolder<DB> viewHolder, int i) {
        if (mDatas != null && mDatas.size() != 0) {
            convert(viewHolder, mDatas.get(i));
        } else {
            if (viewHolder.getBindview() instanceof ItemEmptyBinding) {
                ((ItemEmptyBinding) viewHolder.getBindview()).llEmptyRefresh.setOnClickListener(EmptyClickListener);
            }
        }
    }

    public void onEmptyListener(View.OnClickListener listener) {
        EmptyClickListener = listener;
    }

//    public void setEmptyLayout(int EmptyId) {
//        emptyLayoutId = EmptyId;
//    }

    /**
     * 利用抽象方法回传出去，每个不一样的Adapter去设置
     *
     * @param item 当前的数据
     */
    public abstract void convert(DataBindingViewHolder holder, T item);

    @Override
    public int getItemCount() {
        if (mDatas.size() == 0 && canShowEmpty) {
            return 1;
        }
        if (MaxCount != -1) {
            return MaxCount;
        }
        return this.mDatas.size();
    }

    public void addData(T t) {
        mDatas.add(t);
        notifyDataSetChanged();
    }

    public void clear() {
        mDatas.clear();
    }

    public void addDatas(List<T> list) {
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public void setData(List<T> list) {
        mDatas.clear();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public int getLayoutId(int type) {
        if (type == 0) {
            return R.layout.item_empty;
//            if (emptyLayoutId == -1) {
//                return R.layout.item_empty;
//            } else {
//                return emptyLayoutId;
//            }
        }
        return mLayoutId;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.size() == 0 && canShowEmpty) {
            return EMPTY;
        }
        return NO_EMPTY;
//        return super.getItemViewType(position);
    }

    //是否可以展示空页面
    public void setCanShowEmpty(boolean canShowEmpty) {
        this.canShowEmpty = canShowEmpty;
    }

    public int getDataSize() {
        if (mDatas != null) {
            return mDatas.size();
        } else {
            return 0;
        }
    }

    public List<T> getmDatas() {
        return mDatas;
    }

    public void setMaxCount(int maxCount) {
        MaxCount = maxCount;
    }

    public void setEmptyText(String emptyText) {
        this.emptyText = emptyText;
    }

    public String getEmptyText() {
        return emptyText;
    }
}
