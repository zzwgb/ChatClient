package com.carson.factory.data.helper;

import com.carson.factory.R;
import com.carson.factory.data.DataSource;
import com.carson.factory.model.api.account.RegisterModel;
import com.carson.factory.model.db.User;

/**
 * Author: Create by Carson on 2020/11/19
 */
public class AccountHelper {

    /**
     * 注册的接口，异步的调用
     *
     * @param model    传递一个注册的Model进来
     * @param callback 成功与失败的接口回送
     */
    public static void register(RegisterModel model, final DataSource.Callback<User> callback) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                callback.onDataNotAvailable(R.string.data_rsp_error_parameters);
            }
        }.start();
    }

}
