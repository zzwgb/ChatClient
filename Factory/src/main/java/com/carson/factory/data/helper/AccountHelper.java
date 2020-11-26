package com.carson.factory.data.helper;


import android.text.TextUtils;

import com.carson.factory.Factory;
import com.carson.factory.data.DataSource;
import com.carson.factory.model.api.RspModel;
import com.carson.factory.model.api.account.AccountRspModel;
import com.carson.factory.model.api.account.LoginModel;
import com.carson.factory.model.api.account.RegisterModel;
import com.carson.factory.model.db.User;
import com.carson.factory.net.Network;
import com.carson.factory.net.RemoteService;
import com.carson.factory.persistence.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<AccountRspModel>> call = service.accountRegister(model);
        // 异步的请求
        call.enqueue(new AccountRspCallback(callback));
    }

    /**
     * 登录的调用
     *
     * @param model    登录的Model
     * @param callback 成功与失败的接口回送
     */
    public static void login(final LoginModel model, final DataSource.Callback<User> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<AccountRspModel>> call = service.accountLogin(model);
        // 异步的请求
        call.enqueue(new AccountRspCallback(callback));
    }

    /**
     * 对设备Id进行绑定的操作
     *
     * @param callback Callback
     */
    public static void bindPush(final DataSource.Callback<User> callback) {
        // 检查是否为空
        String pushId = Account.getPushId();
        if (TextUtils.isEmpty(pushId))
            return;

        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        Call<RspModel<AccountRspModel>> call = service.accountBind(pushId);
        call.enqueue(new AccountRspCallback(callback));
    }

    private static class AccountRspCallback implements Callback<RspModel<AccountRspModel>> {

        final DataSource.Callback<User> callback;

        AccountRspCallback(DataSource.Callback<User> callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<RspModel<AccountRspModel>> call, Response<RspModel<AccountRspModel>> response) {
            RspModel<AccountRspModel> rspModel = response.body();
            if (rspModel.success()) { //请求成功返回
                //拿到实体
                AccountRspModel accountRspModel = rspModel.getResult();
                User user = accountRspModel.getUser();
                //写入数据库
                user.save();
                //同步到XML持久化中
                Account.login(accountRspModel);
                if (accountRspModel.isBind()) {
                    //设置绑定状态
                    Account.setBind(true);
                    //回调
                    if (callback != null)
                        callback.onDataLoaded(user);
                } else {
                    //callback.onDataLoaded(accountRspModel.getUser());
                    //进行绑定
                    bindPush(callback);
                }
            } else { //对返回的RspModel中失败的Code进行解析，解析到对应的String资源上面
                Factory.decodeRspCode(rspModel, callback);
            }
        }

        @Override
        public void onFailure(Call<RspModel<AccountRspModel>> call, Throwable t) {
            // 网络请求失败
            if (callback != null) {
                callback.onDataNotAvailable(com.carson.lang.R.string.data_network_error);
            }
        }
    }
}
