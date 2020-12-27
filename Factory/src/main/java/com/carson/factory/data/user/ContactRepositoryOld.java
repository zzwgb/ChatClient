package com.carson.factory.data.user;

import androidx.annotation.NonNull;

import com.carson.factory.data.DataSource;
import com.carson.factory.data.helper.DbHelper;
import com.carson.factory.model.db.User;
import com.carson.factory.model.db.User_Table;
import com.carson.factory.persistence.Account;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import java.util.LinkedList;
import java.util.List;

/**
 * 联系人仓库
 * Author: Create by Carson on 2020/12/23
 */
public class ContactRepositoryOld implements ContactDataSource,
        QueryTransaction.QueryResultListCallback<User>,
        DbHelper.ChangedListener<User> {

    private final List<User> users = new LinkedList<>();
    private DataSource.SucceedCallback<List<User>> callback;

    @Override
    public void load(DataSource.SucceedCallback<List<User>> callback) {
        this.callback = callback;
        //对数据辅助工具类添加一个数据更新的监听
        DbHelper.addChangedListener(User.class, this);
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

    @Override
    public void dispose() {
        this.callback = null;
        DbHelper.removeChangedListener(User.class, this);
    }

    @Override
    public void onListQueryResult(QueryTransaction transaction, @NonNull List<User> tResult) {
        //数据库加载数据成功
        if (tResult.size() == 0) {
            users.clear();
            notifyDataChange();
            return;
        }
        //转为数组
        User[] users = tResult.toArray(new User[0]);
        //回到数据库更新的操作中
        onDataSave(users);
    }

    @Override
    public void onDataSave(User... list) {
        boolean isChanged = false;
        //当数据库数据变更的操作
        for (User user : list) {
            if (isRequired(user)) {
                insertOrUpdate(user);
                isChanged = true;
            }
        }
        // 有数据变更，则进行界面刷新
        if (isChanged)
            notifyDataChange();
    }

    @Override
    public void onDataDelete(User... list) {
        //当数据库数据删除的操作
        boolean isChanged = false;
        for (User user : list) {
            if (users.remove(user))
                isChanged = true;
        }
        // 有数据变更，则进行界面刷新
        if (isChanged)
            notifyDataChange();
    }

    private void insertOrUpdate(User user) {
        int index = indexOf(user);
        if (index >= 0) {
            //更新
            replace(index, user);
        } else {
            //插入
            insert(user);
        }
    }

    private void replace(int index, User user) {
        users.remove(index);
        users.add(index, user);
    }

    private void insert(User user) {
        users.add(user);
    }

    private int indexOf(User user) {
        int index = -1;
        for (User user1 : users) {
            index++;
            if (user1.isSame(user)) {
                return index;
            }
        }
        return -1;
    }

    private void notifyDataChange() {
        if (callback != null)
            callback.onDataLoaded(users);
    }

    /**
     * 检查一个User是否是我需要关注的数据
     *
     * @param user User
     * @return True 表示是我要关注的数据
     */
    private boolean isRequired(User user) {
        return user.isFollow() && !user.getId().equals(Account.getUserId());
    }
}
