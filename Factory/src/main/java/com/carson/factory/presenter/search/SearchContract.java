package com.carson.factory.presenter.search;

import com.carson.factory.model.card.GroupCard;
import com.carson.factory.model.card.UserCard;
import com.carson.factory.presenter.BaseContract;

import java.util.List;

/**
 * Author: Create by Carson on 2020/12/4
 */
public interface SearchContract {

    interface Presenter extends BaseContract.Presenter {
        // 搜索内容
        void search(String content);
    }

    // 搜索人的界面
    interface UserView extends BaseContract.View<Presenter> {
        void onSearchDone(List<UserCard> userCards);
    }

    // 搜索群的界面
    interface GroupView extends BaseContract.View<Presenter> {
        void onSearchDone(List<GroupCard> groupCards);
    }
}
