package com.cn21.innovator.mvvmtestproject.Model.Reposity.remote;

import android.arch.lifecycle.LiveData;

import com.cn21.innovator.mvvmtestproject.API.UserApi;
import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;
import com.cn21.innovator.mvvmtestproject.Model.Bean.Lcee;
import com.cn21.innovator.mvvmtestproject.Model.Reposity.LiveDataObservableAdapter;
import com.cn21.innovator.mvvmtestproject.Model.Reposity.local.Dao.UserDataSource;
import com.cn21.innovator.mvvmtestproject.Model.Utils.RetrofitFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
    return LiveDataObservableAdapter.fromObservableLcee(
            userApi.queryUserByUsername(username)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
    );
  }
}
