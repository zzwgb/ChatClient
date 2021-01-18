package com.carson.factory.presenter.group;

import com.carson.factory.model.Author;
import com.carson.factory.presenter.BaseContract;

/**
 * Author: Create by Carson on 2021/1/17
 */
public interface GroupCreateContract {

    interface Presenter extends BaseContract.Presenter {
        // 创建
        void create(String name, String desc, String picture);

        // 更改一个Model的选中状态
        void changeSelect(ViewModel model, boolean isSelected);
    }

    interface View extends BaseContract.RecyclerView<Presenter, ViewModel> {
        // 创建成功
        void onCreateSucceed();
    }

    class ViewModel {
        // 用户信息
        public Author author;
        // 是否选中
        public boolean isSelected;
    }

}