package com.jpr.test;

import android.content.Context;
import android.widget.Toast;

/**
 * 类描述:测试类
 * 创建日期:2018/2/26 on 10:28
 * 作者:JiaoPeiRong
 */

public class TestClass {
    public  void testFix(Context context){
        int i = 10;
        int a = 0;
        Toast.makeText(context, "test:"+i/a, Toast.LENGTH_SHORT).show();
    }
}
