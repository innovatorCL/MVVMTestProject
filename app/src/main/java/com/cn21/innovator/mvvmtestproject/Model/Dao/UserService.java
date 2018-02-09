package com.cn21.innovator.mvvmtestproject.Model.Dao;

import android.arch.lifecycle.LiveData;

import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;

/**
 * Created by innovator on 2018/2/9.
 */

public interface UserService {

  LiveData<Long> add(GithubUser user);

  LiveData<GithubUser> queryByUsername(String username);
}
