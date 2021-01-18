package com.carson.factory.presenter.group;

import com.carson.factory.model.db.Group;
import com.carson.factory.presenter.BaseContract;

/**
 * 我的群列表契约
 * Author: Create by Carson on 2021/1/18
 */
public interface GroupsContract {
    // 什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter {

    }

    // 都在基类完成了
    interface View extends BaseContract.RecyclerView<Presenter, Group> {

    }
}