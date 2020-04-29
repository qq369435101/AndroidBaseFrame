package com.ysy.module_user;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.CCUtil;
import com.billy.cc.core.component.IComponent;
import com.ysy.common_lib.ActionConstants;
import com.ysy.common_lib.ComponentConstants;
import com.ysy.module_user.ui.LoginActivity;

/**
 * Created by YuShengyang on 2020/1/20
 * Email       ：18210490506@163.com
 * Description ：
 */
public class ComponentUser implements IComponent {
    @Override
    public String getName() {
        return ComponentConstants.ComponentUser;
    }

    @Override
    public boolean onCall(CC cc) {

        switch (cc.getActionName()) {
            case ActionConstants.ActionToLogin: {
                openLoginActivity(cc);
            }
        }
        return false;
    }

    private void openLoginActivity(CC cc) {
        CCUtil.navigateTo(cc, LoginActivity.class);
        CC.sendCCResult(cc.getCallId(), CCResult.success());
    }
}
