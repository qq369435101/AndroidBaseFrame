package com.ysy.common_base.adapter;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected final Context mContext;

    /**
     * 数据集合
     */
    protected List<T> mItemLists = new LinkedList();

    /**
     * 删除条目监听
     */
    protected OnDeleteListener mOnDeleteListener;
    /**
     * 条目操作的回调监听
     */
    protected OnRecyclerViewListener onRecyclerViewListener;

    public BaseRecyclerAdapter(Context context) {
        this.mContext = context;
    }

    public BaseRecyclerAdapter(Context mContext, List<T> mItemLists) {
        this.mContext = mContext;
        if (mItemLists == null) {
            this.mItemLists = new ArrayList<>();
        } else {
            this.mItemLists = mItemLists;
        }
    }

    @Override
    public int getItemCount() {
        return mItemLists.size();
    }

    /**
     * 获取数据集合
     *
     * @return mItemLists
     */
    public List<T> getItemLists() {
        return mItemLists;
    }

    /**
     * 获取数据集合
     *
     * @return mItemLists
     */
    public ArrayList<T> getItemArrayLists() {
        ArrayList<T> arrayList = new ArrayList<T>();
        for (T mItem : mItemLists) {
            arrayList.add(mItem);
        }
        return arrayList;
    }

    /**
     * 设置数据集合
     *
     * @param itemLists
     */
    public void setItemLists(LinkedList<T> itemLists) {
        this.mItemLists = null;
        this.mItemLists = itemLists;
        notifyDataSetChanged();
    }

    /**
     * 刷新数据集合
     *
     * @param itemLists
     */
    public void refreshItemList(List<T> itemLists) {
        setItemLists(itemLists);
    }

    /**
     * 设置数据集合
     *
     * @param itemLists
     */
    public void setItemLists(List<T> itemLists) {
        if (null == itemLists) return;
        this.mItemLists.clear();
        for (int i = 0; i < itemLists.size(); i++) {
            this.mItemLists.add(i, itemLists.get(i));
        }
        notifyDataSetChanged();
    }

    /**
     * 添加数据,默认添加到尾部
     *
     * @param listDatas
     */
    public void add(List<T> listDatas) {
        addToLast(listDatas);
    }

    /**
     * 添加数据到尾部
     *
     * @param listDatas
     */
    public void addToLast(List<T> listDatas) {
        if (listDatas != null) {
            mItemLists.addAll(listDatas);
        }
        notifyDataSetChanged();
    }

    /**
     * @param t
     */
    public void add(T t) {
        if (t != null) {
            mItemLists.add(t);
        }
        notifyDataSetChanged();
    }

    /**
     * 添加数据到首部
     *
     * @param listDatas
     */
    public void addToFirst(List<T> listDatas) {
        if (listDatas != null) {
            for (T data : listDatas) {
                mItemLists.add(0, data);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {
        mItemLists.clear();
        notifyDataSetChanged();
    }

    /**
     * 根据下标删除对应项
     *
     * @param index
     */
    public void deleteForIndex(int index) {
        if ((mOnDeleteListener != null && !mOnDeleteListener
                .onDeleteItem(index)) || index >= getItemCount()) {
            return;
        }
        mItemLists.remove(index);
        notifyItemRemoved(index);
    }

    /**
     * 根据下标删除对应项
     *
     * @param indexs
     */
    public void deleteForIndex(int[] indexs) {
        if (indexs.length <= 0) {
            return;
        }
        Arrays.sort(indexs);
        for (int index = indexs.length - 1; index >= 0; index--) {
            deleteForIndex(indexs[index]);
        }
    }

    /**
     * 设置删除监听器
     *
     * @param onDeleteListener
     */
    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        mOnDeleteListener = onDeleteListener;
    }

    /**
     * 获取当前上下文对象
     */
    protected Context getContext() {
        return this.mContext;
    }

    /**
     * 删除监听接口
     */
    public interface OnDeleteListener {
        boolean onDeleteItem(int index);
    }

    /**
     * 设置条目操作监听
     *
     * @param l
     */
    public void setOnRecyclerViewListener(OnRecyclerViewListener l) {
        this.onRecyclerViewListener = l;
    }

    /**
     * 条目操作回调监听接口
     */
    public interface OnRecyclerViewListener {
        /**
         * 条目点击的监听回调
         *
         * @param position
         */
        void onItemClick(View view, int position);

        /**
         * 长按点击的监听回调
         *
         * @param position
         */
        boolean onItemLongClick(int position);
    }
}
