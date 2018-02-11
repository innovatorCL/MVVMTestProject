package com.cn21.innovator.mvvmtestproject.Model.Bean;

/**
 * 将状态和数据绑定在一起
 * Created by innovator on 2018/2/11.
 */

public class Lcee<T> {

  public final Status status;
  public final T data;
  public final Throwable error;

  public Lcee(Status s,T d,Throwable t){
    this.status = s;
    this.data = d;
    this.error = t;
  }

  public static <T> Lcee<T> content(T data){
    return new Lcee<>(Status.Content,data,null);
  }

  public static <T> Lcee<T> error(T data, Throwable error) {
    return new Lcee<>(Status.Error, data, error);
  }

  public static <T> Lcee<T> error(Throwable error) {
    return error(null, error);
  }

  public static <T> Lcee<T> empty(T data) {
    return new Lcee<>(Status.Empty, data, null);
  }
  public static <T> Lcee<T> empty() {
    return empty(null);
  }

  public static <T> Lcee<T> loading(T data) {
    return new Lcee<>(Status.Loading, data, null);
  }
  public static <T> Lcee<T> loading() {
    return loading(null);
  }
}
