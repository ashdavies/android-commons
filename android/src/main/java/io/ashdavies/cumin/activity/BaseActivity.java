package io.ashdavies.cumin.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.ashdavies.cumin.view.BaseView;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
  private CompositeSubscription subscriptions;
  private Unbinder unbinder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    subscriptions = new CompositeSubscription();

    int layoutId = getLayoutId();
    setContentView(layoutId);
  }

  @NonNull
  protected BaseView getView() {
    return this;
  }

  @LayoutRes
  protected abstract int getLayoutId();

  @Override
  public void setContentView(int layoutResId) {
    super.setContentView(layoutResId);
    unbinder = ButterKnife.bind(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    subscriptions.unsubscribe();
    unbinder.unbind();
  }

  @Override
  public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        && super.shouldShowRequestPermissionRationale(permission);
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
