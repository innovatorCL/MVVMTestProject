package com.cn21.innovator.mvvmtestproject.API;

import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 获取 Github 用户信息
 * Created by innovator on 2018/2/8.
 */

public interface UserApi {
  @GET("/users/{username}")
  Observable<GithubUser> queryUserByUsername(@Path("username") String username);
}
