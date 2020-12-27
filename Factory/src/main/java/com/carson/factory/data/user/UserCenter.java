package com.carson.factory.data.user;

import com.carson.factory.model.card.UserCard;

/**
 * 用户中心的基本定义
 * Author: Create by Carson on 2020/12/19
 */
public interface UserCenter {
    // 分发处理一堆用户卡片的信息，并更新到数据库
    void dispatch(UserCard... cards);
}
