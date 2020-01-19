package com.ysy.module_web.ui;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.CCUtil;
import com.billy.cc.core.component.IComponent;

import static com.ysy.common_lib.ActionConstants.ActionToWeb;
import static com.ysy.common_lib.ComponentConstants.ComponentWeb;

/**
 * Created by YuShengyang on 2020/1/19
 * Email       ：18210490506@163.com
 * Description ：
 */
public class ConponentWeb implements IComponent {
    @Override
    public String getName() {
        return ComponentWeb;
    }

    @Override
    public boolean onCall(CC cc) {
        String action = cc.getActionName();
        switch (action) {
            case ActionToWeb:
                toWeb(cc);
        }
        return false;
    }

    public void toWeb(CC cc) {
        CCUtil.navigateTo(cc, BaseWebviewActivity.class);
        CC.sendCCResult(cc.getCallId(), CCResult.success());
    }
}
