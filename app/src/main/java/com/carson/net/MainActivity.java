package com.carson.net;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.carson.common.app.Activity;
import com.carson.common.widget.PortraitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.qiujuer.genius.ui.widget.FloatActionButton;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @BindView(R.id.appbar)
    View mLayAppbar;

    @BindView(R.id.im_portrait)
    PortraitView mPortrait;

    @BindView(R.id.txt_title)
    TextView mTitle;

    @BindView(R.id.lay_container)
    FrameLayout mContainer;

    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;

    @BindView(R.id.btn_action)
    FloatActionButton mAction;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
    }

    @OnClick(R.id.im_search)
    void onSearchMenuClick() {
        // 在群的界面的时候，点击顶部的搜索就进入群搜索界面
        // 其他都为人搜索的界面
       /* int type = Objects.equals(mNavHelper.getCurrentTab().extra, R.string.title_group) ?
                SearchActivity.TYPE_GROUP : SearchActivity.TYPE_USER;
        SearchActivity.show(this, type);*/
    }

    @OnClick(R.id.btn_action)
    void onActionClick() {
        // 浮动按钮点击时，判断当前界面是群还是联系人界面
        // 如果是群，则打开群创建的界面
      /*  if (Objects.equals(mNavHelper.getCurrentTab().extra, R.string.title_group)) {
            // 打开群创建界面
            GroupCreateActivity.show(this);
        } else {
            // 如果是其他，都打开添加用户的界面
            SearchActivity.show(this, SearchActivity.TYPE_USER);
        }*/
    }
}