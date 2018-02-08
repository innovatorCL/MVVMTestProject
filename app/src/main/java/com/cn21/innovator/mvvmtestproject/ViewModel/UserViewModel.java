package com.cn21.innovator.mvvmtestproject.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.cn21.innovator.mvvmtestproject.Model.Bean.User;

/**
 * 类说明：
 *
 * @author chenl
 * @version 2.1.0
 * @date 2018/1/29
 */
public class UserViewModel extends ViewModel{

  private MutableLiveData<User> user;

  public LiveData<User> getUser(){
    if(user == null){
      user = new MutableLiveData<>();
    }
    return user;
  }

  public void setUserName(String s){
    if(user == null){
      Log.i("TAG","是空对象");
    }else {
      Log.i("TAG","不是空对象");
      Log.i("TAG","user类型："+user.getClass());
      Log.i("TAG","user.getValue()是null吗："+(user.getValue() == null));
      user.setValue(new User(1,s));
    }
  }
}
