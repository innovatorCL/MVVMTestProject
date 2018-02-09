package com.cn21.innovator.mvvmtestproject.Model.Dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;

/**
 * 数据库，由Room 来实例化
 * Created by innovator on 2018/2/9.
 */

@Database(entities = {GithubUser.class}, version = 1, exportSchema = false)
public abstract class DB extends RoomDatabase {

  public abstract UserDao getUserDao();
}
