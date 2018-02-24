package com.cn21.innovator.mvvmtestproject.Model.Reposity.local.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;

/**
 * 定义数据库的操作，定义 Dao 来操作表
 * Created by innovator on 2018/2/9.
 */

@Dao
public interface UserDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE) // cache need update
  Long addUser(GithubUser user);

  @Query("select * from user where login = :username")
  LiveData<GithubUser> queryUserByName(String username);
}
