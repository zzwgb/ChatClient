package com.carson.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.carson.factory.Factory;
import com.carson.factory.data.helper.AccountHelper;
import com.carson.factory.persistence.Account;
import com.igexin.sdk.PushConsts;

/**
 * 个推的消息接收器
 * Author: Create by Carson on 2020/11/23
 */
public class MessageReceiver extends BroadcastReceiver {

    private static final String TAG = MessageReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null)
            return;
        Bundle bundle = intent.getExtras();
        switch (bundle.getInt(PushConsts.CMD_ACTION)) {
            case PushConsts.GET_CLIENTID:
                Log.i(TAG, "GET_CLIENT:" + bundle.toString());
                //当Id初始化的时候，获取设备Id
                onClientInit(bundle.getString("clientid"));
                break;
            case PushConsts.GET_MSG_DATA:
                //常规消息送达
                byte[] payload = bundle.getByteArray("payload");
                if (payload != null) {
                    String message = new String(payload);
                    Log.i(TAG, "GET_MSG_DATA:" + message);
                    onMessageArrived(message);
                }
                break;
            default:
                Log.i(TAG, "OTHER:" + bundle.toString());
                break;
        }
    }

    /**
     * 当Id初始化的时候
     *
     * @param cid 设备id
     */
    private void onClientInit(String cid) {
        //设置设备Id
        Account.setPushId(cid);
        if (Account.isLogin()) {
            //账户登录状态，进行一次PushId绑定
            //没有登录是不能绑定PushId的
            AccountHelper.bindPush(null);
        }
    }

    /**
     * 消息到达时
     *
     * @param message 新消息
     */
    private void onMessageArrived(String message) {
        Factory.dispatchPush(message);
    }
}
