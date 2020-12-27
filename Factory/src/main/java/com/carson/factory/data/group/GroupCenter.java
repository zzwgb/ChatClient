package com.carson.factory.data.group;

import com.carson.factory.model.card.GroupCard;
import com.carson.factory.model.card.GroupMemberCard;

/**
 * 群中心的接口定义
 * Author: Create by Carson on 2020/12/21
 */
public interface GroupCenter {
    // 群卡片的处理
    void dispatch(GroupCard... cards);

    // 群成员的处理
    void dispatch(GroupMemberCard... cards);
}
