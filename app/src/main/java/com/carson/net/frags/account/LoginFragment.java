package com.carson.net.frags.account;


import android.content.Context;

import com.carson.common.app.Fragment;
import com.carson.net.R;

/**
 * 登录界面
 */
public class LoginFragment extends Fragment {

    private AccountTrigger mAccountTrigger;

    public LoginFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 拿到我们的Activity的引用
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onResume() {
        super.onResume();
        //进行一次切换，默认切换为注册界面
        mAccountTrigger.triggerView();
    }
}