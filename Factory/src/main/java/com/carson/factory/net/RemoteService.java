package com.carson.factory.net;

import com.carson.factory.model.api.RspModel;
import com.carson.factory.model.api.account.AccountRspModel;
import com.carson.factory.model.api.account.RegisterModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 网络请求的所有的接口
 */
public interface RemoteService {
    /**
     * 注册接口
     *
     * @param model 传入的是RegisterModel
     * @return 返回的是RspModel<AccountRspModel>
     */
    @POST("account/register")
    Call<RspModel<AccountRspModel>> accountRegister(@Body RegisterModel model);
}
