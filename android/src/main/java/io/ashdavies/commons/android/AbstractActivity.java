package io.ashdavies.commons.android;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import dagger.android.support.DaggerAppCompatActivity;
import io.ashdavies.commons.view.AbstractView;

public abstract class AbstractActivity extends DaggerAppCompatActivity implements AbstractView {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    int layoutId = getLayoutId();
    setContentView(layoutId);
  }

  @LayoutRes
  abstract int getLayoutId();

  @Override
  public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && super.shouldShowRequestPermissionRationale(permission);
  }

  @Override
  public ActionBar getSupportActionBar() {
    ActionBar actionBar = super.getSupportActionBar();
    if (actionBar == null) {
      throw new ActionBarNotSetException();
    }

    return actionBar;
  }

  @Override
  public void onProgress(boolean progressing) {
    /* no op */
  }

  private class ActionBarNotSetException extends RuntimeException {
  }
}
