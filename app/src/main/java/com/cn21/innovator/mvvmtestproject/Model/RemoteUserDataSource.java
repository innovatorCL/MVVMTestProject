package com.cn21.innovator.mvvmtestproject.Model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.cn21.innovator.mvvmtestproject.API.UserApi;
import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;
import com.cn21.innovator.mvvmtestproject.Model.Bean.Lcee;
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
  public LiveData<Lcee<GithubUser>> queryUserByUsername(String username) {
    final MutableLiveData<Lcee<GithubUser>> data = new MutableLiveData<>();
    data.setValue(Lcee.<GithubUser>loading());

    userApi.queryUserByUsername(username)
            .enqueue(new Callback<GithubUser>() {
              @Override
              public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                GithubUser user = response.body();
                if (null == user){
                  //没有数据，也是一种情况，也要改变数据
                  data.setValue(Lcee.<GithubUser>empty());
                  return;
                }
                //LiveData 直接调用 setValue 或 PostValue 就会触发一次数据更新操作
                data.setValue(Lcee.<GithubUser>content(user));

                //保存到本地
                LocalUserDataSource.getInstance().addUser(user);
              }

              @Override
              public void onFailure(Call<GithubUser> call, Throwable t) {
                Log.d("TAG","获取GithubUser出错："+t.getMessage());
                data.setValue(Lcee.<GithubUser>error(t));
              }
            });
    return data;
  }
}
