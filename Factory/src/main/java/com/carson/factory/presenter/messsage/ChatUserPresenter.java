package com.carson.factory.presenter.messsage;

import com.carson.factory.data.helper.UserHelper;
import com.carson.factory.data.message.MessageDataSource;
import com.carson.factory.model.db.Message;
import com.carson.factory.model.db.User;

/**
 * Author: Create by Carson on 2021/1/1
 */
public class ChatUserPresenter extends ChatPresenter<ChatContract.UserView>
        implements ChatContract.Presenter {

    public ChatUserPresenter(ChatContract.UserView view, String receiverId) {
        super(new MessageRepository(receiverId), view,
                receiverId, Message.RECEIVER_TYPE_NONE);
    }

    @Override
    public void start() {
        super.start();

        // 从本地拿这个人的信息
        User receiver = UserHelper.findFromLocal(mReceiverId);
        getView().onInit(receiver);
    }
}
