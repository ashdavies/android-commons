package io.ashdavies.commons.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class AbstractFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    return inflater.inflate(getLayoutId(), container, false);
  }

  @Nullable
  protected View findViewById(@IdRes int resId) {
    if (!isAdded() || getView() == null) {
      return null;
    }

    return getView().findViewById(resId);
  }

  @LayoutRes
  protected abstract int getLayoutId();
}
