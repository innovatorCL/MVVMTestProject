package com.cn21.innovator.mvvmtestproject.Model.Utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * M 层工具类
 * 网络访问库
 * Created by innovator on 2018/2/8.
 */

public class RetrofitFactory {

  private static OkHttpClient okHttpClient;
  private static Retrofit retrofit;

  private static final String HOST = "https://api.github.com";

  static {
    okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(9, TimeUnit.SECONDS)
            .build();

    retrofit = new Retrofit.Builder()
            .baseUrl(HOST)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
  }

  public static Retrofit getInstance(){
    return retrofit;
  }
}
