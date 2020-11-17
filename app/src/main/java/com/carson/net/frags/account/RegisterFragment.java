package com.carson.net.frags.account;

import android.content.Context;

import com.carson.common.app.Fragment;
import com.carson.common.app.PresenterFragment;
import com.carson.factory.account.RegisterContract;
import com.carson.factory.account.RegisterPresenter;
import com.carson.net.R;

/**
 * 注册界面
 */
public class RegisterFragment extends PresenterFragment<RegisterContract.Presenter>
        implements RegisterContract.View {

    private AccountTrigger mAccountTrigger;

    public RegisterFragment() {

    }

    @Override
    protected RegisterContract.Presenter initPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 拿到我们的Activity的引用
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    public void registerSuccess() {

    }

}