package io.ashdavies.cumin.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import io.ashdavies.cumin.view.BaseView;

public abstract class BaseViewPresenter<View extends BaseView> implements ViewPresenter<View> {
  private WeakReference<View> reference;

  @Override
  public void onAttach(@NonNull View view) {
    reference = new WeakReference<>(view);
    onViewAttached(reference.get());
  }

  protected void onViewAttached(@NonNull View view) {
    // Non operation implementation
  }

  @Nullable
  protected View getView() {
    return reference.get();
  }

  protected boolean isAttached() {
    return reference != null && reference.get() != null;
  }

  @Override
  public void onDetach() {
    reference.clear();
  }

  @Override
  public void onError(@NonNull Throwable throwable) {
    View view = getView();
    if (view != null) {
      view.onError(throwable);
    }
  }
}
