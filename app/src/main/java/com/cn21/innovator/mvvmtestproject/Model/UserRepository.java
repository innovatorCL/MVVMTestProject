package com.cn21.innovator.mvvmtestproject.Model;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;
import com.cn21.innovator.mvvmtestproject.Model.Dao.UserDataSource;
import com.cn21.innovator.mvvmtestproject.Model.Utils.NetworkUtils;

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

  private Context context;
  private UserDataSource remoteUserDataSource = RemoteUserDataSource.getInstance();
  private UserDataSource localUserDataSource = LocalUserDataSource.getInstance();


  public void init(Context context) {
    this.context = context.getApplicationContext();
  }

  /**
   * 通过有无网络判断从本地还是服务器获取数据
   * @param username
   * @return
   */
  public LiveData<GithubUser> getUser(String username) {
    if (NetworkUtils.isConnected(context)) {
      return remoteUserDataSource.queryUserByUsername(username);
    } else {
      return localUserDataSource.queryUserByUsername(username);
    }
  }
}
