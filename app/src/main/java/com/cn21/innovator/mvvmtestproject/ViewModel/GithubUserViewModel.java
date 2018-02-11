package com.cn21.innovator.mvvmtestproject.ViewModel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;
import com.cn21.innovator.mvvmtestproject.Model.Bean.Lcee;
import com.cn21.innovator.mvvmtestproject.Model.UserRepository;

/**
 * ViewModel 层，在View层调用M层获取网络数据的方法，然后订阅View层
 * 核心层
 * Created by innovator on 2018/2/8.
 */

public class GithubUserViewModel extends ViewModel{

  private UserRepository userRepository = UserRepository.getInstance();

  private MutableLiveData<String> userName;
  private LiveData<Lcee<GithubUser>> userLiveData;

  /**
   * 使用 Transformations.switchMap 获取 LiveData<GithubUser>
   * 当 userName 改变的时候可以通知 userLiveData 改变
   * @return
   */
  public LiveData<Lcee<GithubUser>> getUserBaseOnName(){
    if(null == userName){
      userName = new MutableLiveData<>();
      userLiveData = Transformations.switchMap(userName, new Function<String, LiveData<Lcee<GithubUser>>>() {
        @Override
        public LiveData<Lcee<GithubUser>> apply(String input) {
          return userRepository.getUser(input);
        }
      });
    }

    return userLiveData;
  }

  /**
   * 改变名字后自动重新改变 userLiveData，从而改变 View
   * @param name
   */
  public void reload(String name){
    if(userName != null){
      userName.setValue(name);
    }
  }
}
