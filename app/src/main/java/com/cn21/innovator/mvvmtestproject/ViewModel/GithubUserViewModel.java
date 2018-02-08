package com.cn21.innovator.mvvmtestproject.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;
import com.cn21.innovator.mvvmtestproject.Model.UserRepository;

/**
 * ViewModel 层，在View层调用M层获取网络数据的方法，然后订阅View层
 * 核心层
 * Created by innovator on 2018/2/8.
 */

public class GithubUserViewModel extends ViewModel{

  private UserRepository userRepository = UserRepository.getInstance();

  private LiveData<GithubUser> userLiveData;

  public LiveData<GithubUser> getUser(String name){
    if(userLiveData == null){
      userLiveData = userRepository.getUser(name);
    }
    return userLiveData;
  }
}
