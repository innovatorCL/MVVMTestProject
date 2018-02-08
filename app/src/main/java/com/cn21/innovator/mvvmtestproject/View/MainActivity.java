package com.cn21.innovator.mvvmtestproject.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cn21.innovator.mvvmtestproject.Model.Bean.User;
import com.cn21.innovator.mvvmtestproject.R;
import com.cn21.innovator.mvvmtestproject.ViewModel.UserViewModel;

public class MainActivity extends AppCompatActivity {

  private TextView tvId;
  private TextView tvName;

  private UserViewModel userViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
    initData();
  }

  private void initView() {
    tvId = this.findViewById(R.id.tv_id);
    tvName = this.findViewById(R.id.tv_name);
  }

  public void goToReposity(View view){
    Intent i = new Intent(this,ReposityActivity.class);
    startActivity(i);
  }

  private void initData() {
    userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

    userViewModel.getUser().observe(this, new Observer<User>() {
      @Override
      public void onChanged(@Nullable User user) {
        updateView(user);
      }
    });

    userViewModel.setUserName("innovator");
  }

  private void updateView(User user){
    tvId.setText(user.getId()+"");
    tvName.setText(user.getName());
  }
}
