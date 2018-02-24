package com.cn21.innovator.mvvmtestproject.Model.Utils;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;
import com.cn21.innovator.mvvmtestproject.Model.Reposity.local.Dao.UserDao;
import com.cn21.innovator.mvvmtestproject.Model.Reposity.local.Dao.UserService;

/**
 * 在子线程使用 DBHelper 进行数据库的读取操作和存操作
 * Created by innovator on 2018/2/9.
 */

public class UserServiceImpl implements UserService{

  private static final UserServiceImpl instance = new UserServiceImpl();

  private UserServiceImpl() {
  }

  public static UserServiceImpl getInstance() {
    return instance;
  }

  private UserDao userDao = DBHelper.getInstance().getDB().getUserDao();

  @Override
  public LiveData<Long> add(final GithubUser user) {

    final MutableLiveData<Long> data = new MutableLiveData<>();
    new AsyncTask<Void,Void,Long>(){
      @Override
      protected void onPostExecute(Long aLong) {
        data.setValue(aLong);
      }

      @Override
      protected Long doInBackground(Void... voids) {
        return userDao.addUser(user);
      }
    }.execute();

    return data;
  }

  @Override
  public LiveData<GithubUser> queryByUsername(String username) {
    return userDao.queryUserByName(username);
  }
}
