package com.cn21.innovator.mvvmtestproject.Model.Bean;

import java.io.Serializable;

/**
 * 类说明：M层 实体类
 *
 * @author chenl
 * @version 2.1.0
 * @date 2018/1/26
 */
public class User implements Serializable{

  private int id;
  private String name;

  public User(int i,String s){
    this.id = i;
    this.name = s;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
