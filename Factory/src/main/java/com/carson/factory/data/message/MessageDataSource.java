package com.carson.factory.data.message;

import com.carson.factory.data.DbDataSource;
import com.carson.factory.model.db.Message;

/**
 * 消息的数据源定义，它的实现是：MessageRepository, MessageGroupRepository
 * 关注的对象是Message表
 * Author: Create by Carson on 2021/1/1
 */
public interface MessageDataSource extends DbDataSource<Message> {
}
