package com.carson.factory.presenter.messsage;

import com.carson.factory.model.db.Session;
import com.carson.factory.presenter.BaseContract;

/**
 * Author: Create by Carson on 2021/1/11
 */
public interface SessionContract {
    // 什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter {

    }

    // 都在基类完成了
    interface View extends BaseContract.RecyclerView<Presenter, Session> {

    }
}
