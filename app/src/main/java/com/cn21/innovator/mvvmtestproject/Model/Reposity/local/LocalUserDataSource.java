package com.cn21.innovator.mvvmtestproject.Model.Reposity.local;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;
import com.cn21.innovator.mvvmtestproject.Model.Bean.Lcee;
import com.cn21.innovator.mvvmtestproject.Model.Reposity.local.Dao.UserDataSource;
import com.cn21.innovator.mvvmtestproject.Model.Reposity.local.Dao.UserService;
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
  public LiveData<Lcee<GithubUser>> queryUserByUsername(String username) {
    final MediatorLiveData<Lcee<GithubUser>> data = new MediatorLiveData<>();
    data.setValue(Lcee.<GithubUser>loading());

    data.addSource(userService.queryByUsername(username), new Observer<GithubUser>() {
      @Override
      public void onChanged(@Nullable GithubUser user) {
        if(null == user){
          data.setValue(Lcee.<GithubUser>empty());
        }else {
          data.setValue(Lcee.content(user));
        }
      }
    });
    return data;
  }


  public LiveData<Long> addUser(GithubUser user){
    return userService.add(user);
  }
}
