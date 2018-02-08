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

    textView = findViewById(R.id.github_user_tv);

    // 其实就是 LiveData<T> .observe(observer);
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
