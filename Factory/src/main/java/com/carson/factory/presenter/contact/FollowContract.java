package com.carson.factory.presenter.contact;

import com.carson.factory.model.card.UserCard;
import com.carson.factory.presenter.BaseContract;

/**
 * 关注的接口定义
 * Author: Create by Carson on 2020/12/10
 */
public interface FollowContract {
    // 任务调度者
    interface Presenter extends BaseContract.Presenter {
        // 关注一个人
        void follow(String id);
    }

    interface View extends BaseContract.View<Presenter> {
        // 成功的情况下返回一个用户的信息
        void onFollowSucceed(UserCard userCard);
    }
}
