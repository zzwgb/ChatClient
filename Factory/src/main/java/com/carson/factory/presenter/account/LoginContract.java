package com.carson.factory.presenter.account;

import androidx.annotation.StringRes;

import com.carson.factory.presenter.BaseContract;

/**
 * Author: Create by Carson on 2020/11/16
 */
public interface LoginContract {

    interface View extends BaseContract.View<Presenter> {
        // 登录成功
        void loginSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        // 发起一个登录
        void login(String phone, String password);

    }
}
