package com.jpr.tinker;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 类描述:
 * 创建日期:2018/2/26 on 10:31
 * 作者:JiaoPeiRong
 */

public class TinkerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        //每次启动都要去修复
        FixDexUtils.loadFixedDex(base);
        super.attachBaseContext(base);
    }
}
