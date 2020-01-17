package com.ysy.module_main;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.CCUtil;
import com.billy.cc.core.component.IComponent;
import com.ysy.common_lib.ComponentConstants;

/**
 * Created by YuShengyang on 2020/1/16
 * Email       ：18210490506@163.com
 * Description ：
 */
public class ConponentMain implements IComponent {
    @Override
    public String getName() {
        return ComponentConstants.ComponentMain;
    }

    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();
        switch (actionName) {
            case "toMain":
                openMainActivity(cc);
                break;
            default:
                break;
        }
        return false;
    }

    private void openMainActivity(CC cc) {
        CCUtil.navigateTo(cc, HomeActivity.class);
        CC.sendCCResult(cc.getCallId(), CCResult.success());
    }
}