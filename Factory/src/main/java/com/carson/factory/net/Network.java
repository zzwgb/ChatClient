package com.carson.factory.net;

import com.carson.common.Common;
import com.carson.factory.Factory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求的封装
 * Author: Create by Carson on 2020/11/19
 */
public class Network {

    // 构建一个Retrofit
    public static Retrofit getRetrofit() {
        //得到一个OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder().build();
        // Retrofit
        Retrofit.Builder builder = new Retrofit.Builder();
        return builder.baseUrl(Common.Constance.API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(Factory.getGson()))
                .build();
    }

    /**
     * 返回一个请求代理
     *
     * @return RemoteService
     */
    public static RemoteService remote() {
        return Network.getRetrofit().create(RemoteService.class);
    }

}
