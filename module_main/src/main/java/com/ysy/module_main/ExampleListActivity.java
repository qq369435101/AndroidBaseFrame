package com.ysy.module_main;


import com.ysy.common_base.adapter.CommonDataBindingViewAdapter;
import com.ysy.common_base.base.BaseRecycleActivity;
import com.ysy.common_base.bean.ExampleBean;
import com.ysy.common_base.viewmodel.MainViewModel;
import com.ysy.module_main.adapter.ExampleAdapter;
import com.ysy.module_main.databinding.ItemExampleBinding;

import java.util.ArrayList;
import java.util.List;

public class ExampleListActivity extends BaseRecycleActivity<ExampleBean, MainViewModel, ItemExampleBinding> {

    @Override
    public void initView() {
        super.initView();
        initTopBar("列表测试");
    }

    @Override
    public void getData() {
        getWindow().getDecorView().postDelayed(() -> {
            showContentView();
            List<ExampleBean> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                ExampleBean bean = new ExampleBean();
                bean.setTv("条目：" + i);
                bean.setIv(R.mipmap.ic_launcher_round);
                list.add(bean);
                finishLoad(true);
            }
            adapter.addData(list);
        }, 1000);
    }

    @Override
    public CommonDataBindingViewAdapter<ExampleBean, ItemExampleBinding> setAdapter() {
        return new ExampleAdapter(this, new ArrayList<>());
    }
}
