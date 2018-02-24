package com.cn21.innovator.mvvmtestproject.View;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.cn21.innovator.mvvmtestproject.Model.Bean.GithubUser;
import com.cn21.innovator.mvvmtestproject.Model.Bean.Lcee;
import com.cn21.innovator.mvvmtestproject.R;
import com.cn21.innovator.mvvmtestproject.ViewModel.GithubUserViewModel;
import com.cn21.innovator.mvvmtestproject.databinding.ActivityReposityBinding;

/**
 * View 层，和 VM层双向绑定
 */
public class ReposityActivity extends AppCompatActivity {

  private ActivityReposityBinding binding;

//  private EditText etUsername;
//  private Button searchButton;
//  private LinearLayout vContent;
//  private FrameLayout vError;
//  private FrameLayout vLoading;
//  private FrameLayout vEmpty;
//
//  private TextView tvId;
//  private TextView tvName;

  GithubUserViewModel githubUserViewModel = new GithubUserViewModel();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initView();
//    setContentView(R.layout.activity_reposity);
//
//    etUsername = findViewById(R.id.et_username);
//    searchButton = findViewById(R.id.btn_search);
//    vContent = findViewById(R.id.v_content);
//    vError = findViewById(R.id.v_error);
//    vLoading = findViewById(R.id.v_loading);
//    vEmpty = findViewById(R.id.v_empty);
//
//    tvId = findViewById(R.id.tv_id);
//    tvName = findViewById(R.id.tv_name);

    initEvent();

    // 其实就是 LiveData<T> .observe(observer);
    // VM 层调用 M 层方法获取数据源，然后绑定 View 层
    initData();
  }

  private void initView(){
    binding = DataBindingUtil.setContentView(this,R.layout.activity_reposity);
    binding.setGithubUser(new GithubUser());
  }

  private void initData(){
    githubUserViewModel.getUserBaseOnName().observe(this, new Observer<Lcee<GithubUser>>() {
      @Override
      public void onChanged(@Nullable Lcee<GithubUser> data) {
        updateUserTv(data);
      }
    });

    reload();
  }

  /**
   * 监听事件，让 V 层处理自己的动作
   */
  private void initEvent() {
    View.OnClickListener reloadClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        hideKeyboard();
        reload();
      }
    };
    binding.vError.setOnClickListener(reloadClickListener);
    binding.vEmpty.setOnClickListener(reloadClickListener);

//    findViewById(R.id.btn_search).setOnClickListener(reloadClickListener);
    binding.btnSearch.setOnClickListener(reloadClickListener);

    binding.etUsername.setOnKeyListener(new View.OnKeyListener() {
      @Override
      public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
          hideKeyboard();
          reload();
          return true;
        }
        return false;
      }
    });
  }

  private void hideKeyboard() {
    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
            .hideSoftInputFromWindow(ReposityActivity.this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
  }

  /**
   * 更新视图
   * @param user
   */
  private void updateUserTv(Lcee<GithubUser> user){
    switch (user.status) {
      case Content:
        showContent();
        binding.setGithubUser(user.data);
//        binding.tvId.setText(user.data.getId() + "");
//        binding.tvName.setText(user.data.getName());
        break;

      case Empty:
        showEmpty();
        break;

      case Error:
        showError();
        break;

      case Loading:
        showLoading();
        break;

      default:
        break;
    }
  }

  private String getUsername() {
    return binding.etUsername.getText().toString();
  }

  /**
   * 通过改变名字来改变 GithubUser 的LiveData，从而改变 View
   */
  private void reload() {
    // reload
    githubUserViewModel.reload(getUsername());
  }


  private void showContent() {
    binding.vContent.setVisibility(View.VISIBLE);
    binding.vEmpty.setVisibility(View.GONE);
    binding.vError.setVisibility(View.GONE);
    binding.vLoading.setVisibility(View.GONE);
  }

  private void showEmpty() {
    binding.vContent.setVisibility(View.GONE);
    binding.vEmpty.setVisibility(View.VISIBLE);
    binding.vError.setVisibility(View.GONE);
    binding.vLoading.setVisibility(View.GONE);
  }

  private void showError() {
    binding.vContent.setVisibility(View.GONE);
    binding.vEmpty.setVisibility(View.GONE);
    binding.vError.setVisibility(View.VISIBLE);
    binding.vLoading.setVisibility(View.GONE);
  }

  private void showLoading() {
    binding.vContent.setVisibility(View.GONE);
    binding.vEmpty.setVisibility(View.GONE);
    binding.vError.setVisibility(View.GONE);
    binding.vLoading.setVisibility(View.VISIBLE);
  }
}
