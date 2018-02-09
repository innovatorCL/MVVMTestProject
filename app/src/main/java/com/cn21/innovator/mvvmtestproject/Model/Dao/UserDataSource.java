package com.cn21.innovator.mvvmtestproject.Model.Dao;

import android.arch.lifecycle.LiveData;

import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;

/**
 * 对外提供统一的数据源，由 LocalUserDataSource 和 RemoteUserDataSource 实现
 * Created by innovator on 2018/2/9.
 */

public interface UserDataSource {

  LiveData<GithubUser> queryUserByUsername(String username);
}
