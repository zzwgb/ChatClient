package com.carson.factory.presenter.user;

import com.carson.factory.presenter.BaseContract;

/**
 * 更新用户信息的基本的契约
 */
public interface UpdateInfoContract {

    interface View extends BaseContract.View<Presenter> {
        // 回调成功
        void updateSucceed();
    }

    interface Presenter extends BaseContract.Presenter {
        // 更新
        void update(String photoFilePath, String desc, boolean isMan);
    }
}
