package com.carson.factory.data.user;


import com.carson.factory.data.BaseDbRepository;
import com.carson.factory.data.DataSource;
import com.carson.factory.model.db.User;
import com.carson.factory.model.db.User_Table;
import com.carson.factory.persistence.Account;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;


/**
 * 联系人仓库
 * Author: Create by Carson on 2020/12/23
 */
public class ContactRepository extends BaseDbRepository<User>
        implements ContactDataSource {

    @Override
    public void load(DataSource.SucceedCallback<List<User>> callback) {
        super.load(callback);

        //加载本地数据
        SQLite.select()
                .from(User.class)
                .where(User_Table.isFollow.eq(true))
                .and(User_Table.id.notEq(Account.getUserId()))
                .orderBy(User_Table.name, true)
                .limit(100)
                .async()
                .queryListResultCallback(this)
                .execute();

    }

    /**
     * 检查一个User是否是我需要关注的数据
     *
     * @param user User
     * @return True 表示是我要关注的数据
     */
    @Override
    protected boolean isRequired(User user) {
        return user.isFollow() && !user.getId().equals(Account.getUserId());
    }
}
