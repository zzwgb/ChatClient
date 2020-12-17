package com.carson.factory.presenter.contact;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.carson.factory.data.DataSource;
import com.carson.factory.data.helper.UserHelper;
import com.carson.factory.model.card.UserCard;
import com.carson.factory.model.db.AppDatabase;
import com.carson.factory.model.db.User;
import com.carson.factory.model.db.User_Table;
import com.carson.factory.persistence.Account;
import com.carson.factory.presenter.BasePresenter;
import com.carson.factory.utils.DiffUiDataCallback;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.CursorResult;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * 联系人的Presenter实现
 * Author: Create by Carson on 2020/12/15
 */
public class ContactPresenter extends BasePresenter<ContactContract.View>
        implements ContactContract.Presenter {

    public ContactPresenter(ContactContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        super.start();

        //加载本地数据
        SQLite.select()
                .from(User.class)
                .where(User_Table.isFollow.eq(true))
                .and(User_Table.id.notEq(Account.getUserId()))
                .orderBy(User_Table.name, true)
                .limit(100)
                .async()
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<User>() {
                    @Override
                    public void onListQueryResult(QueryTransaction transaction, @NonNull List<User> tResult) {
                        getView().getRecyclerAdapter().replace(tResult);
                        getView().onAdapterDataChanged();
                    }
                }).execute();

        //加载网络数据
        UserHelper.refreshContacts(new DataSource.Callback<List<UserCard>>() {

            @Override
            public void onDataNotAvailable(int strRes) {

            }

            @Override
            public void onDataLoaded(List<UserCard> userCards) {
                //UserCard转为User
                final List<User> users = new ArrayList<>();
                for (UserCard userCard : userCards)
                    users.add(userCard.build());
                //保存数据库
                DatabaseDefinition definition = FlowManager.getDatabase(AppDatabase.class);
                definition.beginTransactionAsync(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        FlowManager.getModelAdapter(User.class)
                                .saveAll(users);
                    }
                }).build().execute();
                //本地数据和网络数据对比
                diff(getView().getRecyclerAdapter().getItems(), users);
            }
        });
    }

    private void diff(List<User> oldList, List<User> newList) {
        //进行数据比较
        DiffUtil.Callback callback = new DiffUiDataCallback<User>(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        //在对比完成后进行数据的赋值
        getView().getRecyclerAdapter().replace(newList);
        //尝试刷新界面
        result.dispatchUpdatesTo(getView().getRecyclerAdapter());
        getView().onAdapterDataChanged();
    }

}
