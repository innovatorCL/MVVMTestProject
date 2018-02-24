package com.cn21.innovator.mvvmtestproject.Model.Utils;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.cn21.innovator.mvvmtestproject.Model.Reposity.local.Dao.DB;

/**
 * 数据库工具类
 *
 * 构造数据库
 * Created by innovator on 2018/2/9.
 */

public class DBHelper {

  private static final DBHelper instance = new DBHelper();
  private static final String DATABASE_NAME = "c_cache";

  private DB db;

  private DBHelper(){

  }

  public static DBHelper getInstance(){
    return instance;
  }

  public void init(Context context){
    if(db == null){
      //构造数据库，在 Application 或者 Activity 的 onCreate（）中初始化
      db = Room.databaseBuilder(context.getApplicationContext(),DB.class,DATABASE_NAME).build();
    }
  }

  public DB getDB(){
    return db;
  }
}
