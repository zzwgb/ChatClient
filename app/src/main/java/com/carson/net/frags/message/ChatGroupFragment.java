package com.carson.net.frags.message;

import com.carson.factory.model.db.Group;
import com.carson.factory.model.db.view.MemberUserModel;
import com.carson.factory.presenter.messsage.ChatContract;
import com.carson.factory.presenter.messsage.ChatGroupPresenter;
import com.carson.net.R;

import java.util.List;

/**
 * 群聊页面
 */
public class ChatGroupFragment extends ChatFragment<Group>
        implements ChatContract.GroupView {

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
    public void onInit(Group group) {

    }

    @Override
    public void showAdminOption(boolean isAdmin) {

    }

    @Override
    public void onInitGroupMembers(List<MemberUserModel> members, long moreCount) {

    }

}