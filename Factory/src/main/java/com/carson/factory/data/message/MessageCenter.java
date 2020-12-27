package com.carson.factory.data.message;

import com.carson.factory.model.card.MessageCard;

/**
 * 消息中心，进行消息卡片的消费
 * Author: Create by Carson on 2020/12/21
 */
public interface MessageCenter {
    void dispatch(MessageCard... cards);
}
