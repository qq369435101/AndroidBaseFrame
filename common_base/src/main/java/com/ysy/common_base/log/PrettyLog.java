package com.ysy.common_base.log;

import java.lang.reflect.Method;

/**
 * Created by xianglanzuo on 2017/12/13.
 */

public class PrettyLog {

    private static Method sJsonMethod, sDevpMethod;

    static {
        canPrintPretty();
    }

    public static boolean canPrintPretty() {
        try {
            Class<?> loggerClass = Class.forName("com.orhanobut.logger.Logger");
            sJsonMethod = loggerClass.getDeclaredMethod("json", String.class);
            sDevpMethod = loggerClass.getDeclaredMethod("d", String.class, Object[].class);
            sJsonMethod.setAccessible(true);
            sDevpMethod.setAccessible(true);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean json(String json) {
        try {
            sJsonMethod.invoke(null, json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean d(String message, Object... args) {
        try {
            sDevpMethod.invoke(null, message, args);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
