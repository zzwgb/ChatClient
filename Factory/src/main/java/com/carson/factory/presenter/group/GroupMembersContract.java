package com.carson.factory.presenter.group;

import com.carson.factory.model.db.view.MemberUserModel;
import com.carson.factory.presenter.BaseContract;

/**
 * 群成员的契约
 * Author: Create by Carson on 2021/1/19
 */
public interface GroupMembersContract {
    interface Presenter extends BaseContract.Presenter {
        // 具有一个刷新的方法
        void refresh();
    }

    // 界面
    interface View extends BaseContract.RecyclerView<Presenter, MemberUserModel> {
        // 获取群的ID
        String getGroupId();
    }
}
