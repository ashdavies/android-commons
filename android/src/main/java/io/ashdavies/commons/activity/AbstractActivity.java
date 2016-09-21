package io.ashdavies.commons.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import io.ashdavies.commons.view.AbstractView;

public abstract class AbstractActivity extends AppCompatActivity implements AbstractView {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    int layoutId = getLayoutId();
    setContentView(layoutId);
  }

  @NonNull
  protected AbstractView getView() {
    return this;
  }

  @LayoutRes
  protected abstract int getLayoutId();

  @Override
  public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && super.shouldShowRequestPermissionRationale(permission);
  }

  public Context getContext() {
    return this;
  }

  @Override
  public void showProgress() {
    // Do nothing
  }

  @Override
  public void hideProgress() {
    // Do nothing
  }

  public void setActionBarTitle(@StringRes int title) {
    setActionBarTitle(getResources().getString(title));
  }

  public void setActionBarTitle(CharSequence title) {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle(title);
    }
  }
}
