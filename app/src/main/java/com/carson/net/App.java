package com.carson.net;

import com.carson.common.app.Application;
import com.carson.factory.Factory;
import com.igexin.sdk.PushManager;

/**
 * Author: Create by Carson on 2020/11/6
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //调用Factory初始化SP
        Factory.setup();
        // 推送进行初始化
        PushManager.getInstance().initialize(App.this, AppPushService.class);
        // 推送注册消息接收服务
        PushManager.getInstance().registerPushIntentService(App.this, AppMessageReceiverService.class);
    }
}
