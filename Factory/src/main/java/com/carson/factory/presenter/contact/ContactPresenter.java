package com.carson.factory.presenter.contact;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.carson.common.widget.recycler.RecyclerAdapter;
import com.carson.factory.data.DataSource;
import com.carson.factory.data.helper.UserHelper;

import com.carson.factory.data.user.ContactDataSource;
import com.carson.factory.data.user.ContactRepository;
import com.carson.factory.model.db.User;

import com.carson.factory.presenter.BasePresenter;
import com.carson.factory.presenter.BaseRecyclerPresenter;
import com.carson.factory.presenter.BaseSourcePresenter;
import com.carson.factory.utils.DiffUiDataCallback;


import java.util.ArrayList;
import java.util.List;

/**
 * 联系人的Presenter实现
 * Author: Create by Carson on 2020/12/15
 */
public class ContactPresenter extends
        BaseSourcePresenter<User, User, ContactDataSource, ContactContract.View>
        implements ContactContract.Presenter,
        DataSource.SucceedCallback<List<User>> {


    public ContactPresenter(ContactContract.View view) {
        //初始化数据仓库
        super(new ContactRepository(), view);
    }

    @Override
    public void start() {
        super.start();
        //加载网络数据
        UserHelper.refreshContacts();
    }


    /*
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
    * */


    //运行到这里是在子线程
    @Override
    public void onDataLoaded(List<User> users) {
        //无论是本地/网络数据的变更，最终都会通知这里来
        final ContactContract.View view = getView();
        if (view == null)
            return;
        RecyclerAdapter<User> adapter = view.getRecyclerAdapter();
        List<User> oldList = adapter.getItems();

        //进行数据对比
        DiffUtil.Callback callback = new DiffUiDataCallback<User>(oldList, users);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        //调用基类方法进行界面刷新
        refreshData(result, users);
    }
}
