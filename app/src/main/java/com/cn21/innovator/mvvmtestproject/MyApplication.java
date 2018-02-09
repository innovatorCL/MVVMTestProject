package com.cn21.innovator.mvvmtestproject;

import android.app.Application;

import com.cn21.innovator.mvvmtestproject.Model.UserRepository;
import com.cn21.innovator.mvvmtestproject.Model.Utils.DBHelper;

/**
 * Created by innovator on 2018/2/9.
 */

public class MyApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    //初始化数据库
    DBHelper.getInstance().init(this);
    //传入 Context 给 Model 层判断该使用远程还是本地的数据源
    UserRepository.getInstance().init(this);
  }
}
