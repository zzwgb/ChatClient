package com.carson.factory.presenter.search;

import com.carson.factory.presenter.BasePresenter;

/**
 * Author: Create by Carson on 2020/12/4
 */
public class SearchGroupPresenter extends BasePresenter<SearchContract.GroupView>
        implements SearchContract.Presenter {

    public SearchGroupPresenter(SearchContract.GroupView view) {
        super(view);
    }

    @Override
    public void search(String content) {

    }
}
