package com.carson.net.activities;


import android.content.Context;
import android.content.Intent;

import com.carson.common.app.Activity;
import com.carson.net.R;
import com.carson.net.frags.account.UpdateInfoFragment;

public class AccountActivity extends Activity {

    /**
     * 账户Activity显示的入口
     *
     * @param context Context
     */
    public static void show(Context context) {
        context.startActivity(new Intent(context, AccountActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        //初始化Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_container, new UpdateInfoFragment())
                .commit();
    }
}