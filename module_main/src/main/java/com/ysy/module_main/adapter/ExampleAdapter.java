package com.ysy.module_main.adapter;

import android.content.Context;
import android.util.Log;

import com.ysy.common_base.adapter.CommonDataBindingViewAdapter;
import com.ysy.common_base.bean.ExampleBean;
import com.ysy.module_main.R;
import com.ysy.module_main.databinding.ItemExampleBinding;

import java.util.List;

/**
 * Created by YuShengyang on 2020/5/12
 * Email       ：18210490506@163.com
 * Description ：
 */
public class ExampleAdapter extends CommonDataBindingViewAdapter<ExampleBean, ItemExampleBinding> {
    public ExampleAdapter(Context context, List<ExampleBean> datas) {
        super(context, datas, R.layout.item_example);
    }


    @Override
    public void convert(ItemExampleBinding dataBinding, ExampleBean item) {
        Log.e("listLog", "position:" + getmDatas().indexOf(item));
        dataBinding.setBean(item);
//        dataBinding.tv.setText(item.getTv());
//        dataBinding.iv.setImageResource(item.getIv());
    }
}
