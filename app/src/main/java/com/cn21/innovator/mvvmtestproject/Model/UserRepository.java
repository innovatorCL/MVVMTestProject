package com.cn21.innovator.mvvmtestproject.Model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.cn21.innovator.mvvmtestproject.API.UserApi;
import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;
import com.cn21.innovator.mvvmtestproject.Model.Utils.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Github User的数据源
 * 看成网络访问后拿到的Model层
 * Created by innovator on 2018/2/8.
 */

public class UserRepository {

  private static final UserRepository instance = new UserRepository();

  private UserRepository() {
  }

  public static UserRepository getInstance() {
    return instance;
  }

  private UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);


  public LiveData<GithubUser> getUser(String name){
    final MutableLiveData<GithubUser> user = new MutableLiveData<>();
    userApi.queryUserByUsername(name)
            .enqueue(new Callback<GithubUser>() {
              @Override
              public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                user.setValue(response.body());
              }

              @Override
              public void onFailure(Call<GithubUser> call, Throwable t) {
                Log.d("TAG","获取GithubUser出错："+t.getMessage());
              }
            });

    return user;
  }
}
