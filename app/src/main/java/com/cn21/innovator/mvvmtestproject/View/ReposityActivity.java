package com.cn21.innovator.mvvmtestproject.View;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;
import com.cn21.innovator.mvvmtestproject.R;
import com.cn21.innovator.mvvmtestproject.ViewModel.GithubUserViewModel;

/**
 * View 层，和 VM层双向绑定
 */
public class ReposityActivity extends AppCompatActivity {

  private TextView textView;

  GithubUserViewModel githubUserViewModel = new GithubUserViewModel();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reposity);

    /*//初始化数据库
    DBHelper.getInstance().init(this);
    //传入 Context 给 Model 层判断该使用远程还是本地的数据源
    UserRepository.getInstance().init(this);*/

    textView = findViewById(R.id.github_user_tv);

    // 其实就是 LiveData<T> .observe(observer);
    // VM 层调用 M 层方法获取数据源，然后绑定 View 层
    githubUserViewModel.getUser("innovatorCL").observe(this, new Observer<GithubUser>() {
      @Override
      public void onChanged(@Nullable GithubUser githubUser) {
        updateUserTv(githubUser);
      }
    });
  }

  private void updateUserTv(GithubUser user){
    textView.setText(user.toString());
  }
}
