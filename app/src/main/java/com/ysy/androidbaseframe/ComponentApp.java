package com.ysy.androidbaseframe;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.IComponent;

import static com.ysy.common_lib.ComponentConstants.ComponentAPP;

/**
 * Created by YuShengyang on 2020/1/19
 * Email       ：18210490506@163.com
 * Description ：
 */
public class ComponentApp implements IComponent {
    @Override
    public String getName() {
        return ComponentAPP;
    }

    @Override
    public boolean onCall(CC cc) {
        return false;
    }
}
