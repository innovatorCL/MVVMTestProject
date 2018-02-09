package com.cn21.innovator.mvvmtestproject.Model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.cn21.innovator.mvvmtestproject.API.UserApi;
import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;
import com.cn21.innovator.mvvmtestproject.Model.Dao.UserDataSource;
import com.cn21.innovator.mvvmtestproject.Model.Utils.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by innovator on 2018/2/9.
 */

public class RemoteUserDataSource implements UserDataSource {

  private static final RemoteUserDataSource instance = new RemoteUserDataSource();
  private RemoteUserDataSource() {
  }
  public static RemoteUserDataSource getInstance() {
    return instance;
  }

  //生成一个动态代理对象
  private UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);

  @Override
  public LiveData<GithubUser> queryUserByUsername(String username) {
    final MutableLiveData<GithubUser> data = new MutableLiveData<>();
    userApi.queryUserByUsername(username)
            .enqueue(new Callback<GithubUser>() {
              @Override
              public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                GithubUser user = response.body();
                if (null == user){
                  return;
                }
                data.setValue(user);

                //保存到本地
                LocalUserDataSource.getInstance().addUser(user);
              }

              @Override
              public void onFailure(Call<GithubUser> call, Throwable t) {
                Log.d("TAG","获取GithubUser出错："+t.getMessage());
              }
            });
    return data;
  }
}
