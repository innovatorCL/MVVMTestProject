package com.cn21.innovator.mvvmtestproject.Model;

import android.arch.lifecycle.LiveData;

import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;
import com.cn21.innovator.mvvmtestproject.Model.Dao.UserDataSource;
import com.cn21.innovator.mvvmtestproject.Model.Dao.UserService;
import com.cn21.innovator.mvvmtestproject.Model.Utils.UserServiceImpl;

/**
 * 本地的数据源
 * Created by innovator on 2018/2/9.
 */

public class LocalUserDataSource implements UserDataSource{

  private static final LocalUserDataSource instance = new LocalUserDataSource();
  private LocalUserDataSource() {
  }
  public static LocalUserDataSource getInstance() {
    return instance;
  }

  private UserService userService = UserServiceImpl.getInstance();

  /**
   * 从本地数据库加载数据
   * @param username
   * @return
   */
  @Override
  public LiveData<GithubUser> queryUserByUsername(String username) {
    return userService.queryByUsername(username);
  }


  public LiveData<Long> addUser(GithubUser user){
    return userService.add(user);
  }
}
