package debug;

import com.billy.cc.core.component.CC;

/**
 * Created by YuShengyang on 2020/1/15
 * Email ：18210490506@163.com
 */
public class UserApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CC.enableVerboseLog(true);
        CC.enableDebug(true);
        CC.enableRemoteCC(true);
    }
}
