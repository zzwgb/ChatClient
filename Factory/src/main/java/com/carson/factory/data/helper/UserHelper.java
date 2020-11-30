package com.carson.factory.data.helper;

import com.carson.factory.Factory;
import com.carson.factory.R;
import com.carson.factory.data.DataSource;
import com.carson.factory.model.api.RspModel;
import com.carson.factory.model.api.user.UserUpdateModel;
import com.carson.factory.model.card.UserCard;
import com.carson.factory.model.db.User;
import com.carson.factory.net.Network;
import com.carson.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author: Create by Carson on 2020/11/30
 */
public class UserHelper {

    // 更新用户信息的操作，异步的
    public static void update(UserUpdateModel model, final DataSource.Callback<UserCard> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<UserCard>> call = service.userUpdate(model);
        // 网络请求
        call.enqueue(new Callback<RspModel<UserCard>>() {
            @Override
            public void onResponse(Call<RspModel<UserCard>> call, Response<RspModel<UserCard>> response) {
                RspModel<UserCard> rspModel = response.body();
                if (rspModel.success()) {
                    UserCard userCard = rspModel.getResult();
                    // 唤起进行保存的操作
                    User user = userCard.build();
                    user.save();
                    //Factory.getUserCenter().dispatch(userCard);
                    // 返回成功
                    callback.onDataLoaded(userCard);
                } else {
                    // 错误情况下进行错误分配
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<UserCard>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }
}
