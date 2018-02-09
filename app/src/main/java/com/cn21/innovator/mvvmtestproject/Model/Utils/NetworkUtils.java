package com.cn21.innovator.mvvmtestproject.Model.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络检查工具类
 * Created by innovator on 2018/2/9.
 */

public class NetworkUtils {

  /**
   * 检查是都有可用的网络
   * @param context
   * @return
   */
  public static boolean isConnected(Context context) {
    if (context != null) {
      ConnectivityManager mConnectivityManager = (ConnectivityManager) context
              .getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
      if (mNetworkInfo != null) {
        return mNetworkInfo.isAvailable();
      }
    }
    return false;
  }
}
