package com.carson.factory.presenter.messsage;

import androidx.recyclerview.widget.DiffUtil;

import com.carson.factory.data.message.SessionDataSource;
import com.carson.factory.data.message.SessionRepository;
import com.carson.factory.model.db.Session;
import com.carson.factory.presenter.BaseSourcePresenter;
import com.carson.factory.utils.DiffUiDataCallback;

import java.util.List;

/**
 * Author: Create by Carson on 2021/1/11
 */
public class SessionPresenter extends BaseSourcePresenter<Session, Session,
        SessionDataSource, SessionContract.View> implements SessionContract.Presenter {

    public SessionPresenter(SessionContract.View view) {
        super(new SessionRepository(), view);
    }

    @Override
    public void onDataLoaded(List<Session> sessions) {
        SessionContract.View view = getView();
        if (view == null)
            return;

        // 差异对比
        List<Session> old = view.getRecyclerAdapter().getItems();
        DiffUiDataCallback<Session> callback = new DiffUiDataCallback<>(old, sessions);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        // 刷新界面
        refreshData(result, sessions);
    }
}
