package com.carson.factory.account;

import com.carson.factory.presenter.BasePresenter;

/**
 * Author: Create by Carson on 2020/11/17
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    public RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public void register(String phone, String name, String password) {

    }

    @Override
    public boolean checkMobile(String phone) {
        return false;
    }

}
