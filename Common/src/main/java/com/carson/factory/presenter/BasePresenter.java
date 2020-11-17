package com.carson.factory.presenter;

/**
 * Author: Create by Carson on 2020/11/17
 */
public class BasePresenter<T extends BaseContract.View>
        implements BaseContract.Presenter {

    protected T mView;

    public BasePresenter(T view) {
    }

    protected void setView(T view) {
        this.mView = view;
    }

    protected final T getView() {
        return mView;
    }

    @Override
    public void start() {
        // 开始的时候进行Loading调用
        T view = mView;
        if (view != null) {
            view.showLoading();
        }
    }

    @Override
    public void destroy() {
        T view = mView;
        mView = null;
        if (view != null) {
            // 把Presenter设置为NULL
            view.setPresenter(null);
        }
    }
}
