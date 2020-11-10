package com.carson.net;

import android.os.Bundle;

import com.carson.common.app.Activity;
import com.carson.net.frags.assist.PermissionFragment;

public class LauncherActivity extends Activity {
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launcher;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PermissionFragment.haveAll(this, getSupportFragmentManager())) {
            MainActivity.show(this);
            finish();
        }
    }
}