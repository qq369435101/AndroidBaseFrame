package com.ysy.module_main;


import com.ysy.common_base.adapter.CommonDataBindingViewAdapter;
import com.ysy.common_base.base.BaseRecycleActivity;
import com.ysy.common_base.bean.ExampleBean;
import com.ysy.module_main.adapter.ExampleAdapter;
import com.ysy.module_main.databinding.ItemExampleBinding;
import com.ysy.module_network.viewmodel.MainViewModel;

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
            int count = adapter.getItemCount();
            for (int i = 0; i < 10; i++) {
                ExampleBean bean = new ExampleBean();
                bean.setTv("条目：" + (count + i));
                if (i % 2 == 1) {
                    bean.setIv(R.mipmap.ic_launcher_round);
                } else {
                    bean.setIv(R.mipmap.icon_more_operation_share_weibo);
                }
                list.add(bean);
            }
            adapter.addData(list);
            finishLoad(true);
        }, 1000);
    }

    @Override
    public CommonDataBindingViewAdapter<ExampleBean, ItemExampleBinding> setAdapter() {
        return new ExampleAdapter(this, new ArrayList<>());
    }
}
