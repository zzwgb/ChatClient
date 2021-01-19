package com.carson.net.frags.message;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.carson.factory.model.db.Group;
import com.carson.factory.model.db.view.MemberUserModel;
import com.carson.factory.presenter.messsage.ChatContract;
import com.carson.factory.presenter.messsage.ChatGroupPresenter;
import com.carson.net.R;
import com.carson.net.activities.GroupMemberActivity;
import com.carson.net.activities.PersonalActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import butterknife.BindView;

/**
 * 群聊页面
 */
public class ChatGroupFragment extends ChatFragment<Group>
        implements ChatContract.GroupView {

    @BindView(R.id.im_header)
    ImageView mHeader;

    @BindView(R.id.lay_members)
    LinearLayout mLayMembers;

    @BindView(R.id.txt_member_more)
    TextView mMemberMore;

    public ChatGroupFragment() {
    }

    /* 没法再复写了，基类已经final了
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_chat_group;
    }*/


    @Override
    protected int getHeaderLayoutId() {
        return R.layout.lay_chat_header_group;
    }

    @Override
    protected ChatContract.Presenter initPresenter() {
        return new ChatGroupPresenter(this, mReceiverId);
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        Glide.with(this)
                .load(R.drawable.default_banner_group)
                .centerCrop()
                .into(new CustomViewTarget<CollapsingToolbarLayout, Drawable>(mCollapsingLayout) {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        this.view.setContentScrim(resource);
                    }

                    @Override
                    protected void onResourceCleared(@Nullable Drawable placeholder) {
                    }
                });
    }

    // 进行高度的综合运算，透明我们的头像和Icon
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        super.onOffsetChanged(appBarLayout, verticalOffset);
        View view = mLayMembers;

        if (view == null)
            return;

        if (verticalOffset == 0) {
            // 完全展开
            view.setVisibility(View.VISIBLE);
            view.setScaleX(1);
            view.setScaleY(1);
            view.setAlpha(1);
        } else {
            // abs 运算
            verticalOffset = Math.abs(verticalOffset);
            final int totalScrollRange = appBarLayout.getTotalScrollRange();
            if (verticalOffset >= totalScrollRange) {
                // 关闭状态
                view.setVisibility(View.INVISIBLE);
                view.setScaleX(0);
                view.setScaleY(0);
                view.setAlpha(0);
            } else {
                // 中间状态
                float progress = 1 - verticalOffset / (float) totalScrollRange;
                view.setVisibility(View.VISIBLE);
                view.setScaleX(progress);
                view.setScaleY(progress);
                view.setAlpha(progress);
            }
        }
    }

    @Override
    public void onInit(Group group) {
        mCollapsingLayout.setTitle(group.getName());
        Glide.with(this)
                .load(group.getPicture())
                .centerCrop()
                .placeholder(R.drawable.default_banner_group)
                .into(mHeader);
    }

    @Override
    public void showAdminOption(boolean isAdmin) {
        if (isAdmin) {
            mToolbar.inflateMenu(R.menu.chat_group);
            mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    // 群成员添加操作
                    if (item.getItemId() == R.id.action_add) {
                        // mReceiverId 就是群的Id
                        //GroupMemberActivity.showAdmin(getContext(), mReceiverId);
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void onInitGroupMembers(List<MemberUserModel> members, long moreCount) {
        if (members == null || members.size() == 0)
            return;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (final MemberUserModel member : members) {
            // 添加成员头像
            ImageView p = (ImageView) inflater.inflate(R.layout.lay_chat_group_portrait, mLayMembers, false);
            mLayMembers.addView(p, 0);

            Glide.with(this)
                    .load(member.portrait)
                    .placeholder(R.drawable.default_portrait)
                    .centerCrop()
                    .dontAnimate()
                    .into(p);
            // 个人界面信息查看
            p.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PersonalActivity.show(getContext(), member.userId);
                }
            });
        }

        // 更多的按钮
        if (moreCount > 0) {
            mMemberMore.setText(String.format("+%s", moreCount));
            mMemberMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // mReceiverId 就是群的Id
                    // GroupMemberActivity.show(getContext(), mReceiverId);
                }
            });
        } else {
            mMemberMore.setVisibility(View.GONE);
        }
    }

}