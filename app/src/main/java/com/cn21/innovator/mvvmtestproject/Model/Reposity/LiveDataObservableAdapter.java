package com.cn21.innovator.mvvmtestproject.Model.Reposity;

import android.arch.lifecycle.LiveData;

import com.cn21.innovator.mvvmtestproject.Model.Bean.Lcee;
import com.cn21.innovator.mvvmtestproject.Model.Bean.ObservableLceeLiveData;

import io.reactivex.Observable;

/**
 * Created by innovator on 2018/2/24.
 */

public class LiveDataObservableAdapter {

  public static <T> LiveData<Lcee<T>> fromObservableLcee(final Observable<T> observable) {
    return new ObservableLceeLiveData<>(observable);
  }

}
