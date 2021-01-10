package com.carson.net.frags.message;

import com.carson.factory.model.db.Group;
import com.carson.factory.presenter.messsage.ChatContract;
import com.carson.net.R;

/**
 * 群聊页面
 */
public class ChatGroupFragment extends ChatFragment<Group>
        implements ChatContract.GroupView {

    public ChatGroupFragment() {
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_chat_group;
    }

    @Override
    protected ChatContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onInit(Group group) {

    }
}