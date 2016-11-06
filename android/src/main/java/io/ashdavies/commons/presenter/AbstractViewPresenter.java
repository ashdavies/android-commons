package io.ashdavies.commons.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import io.ashdavies.commons.Preconditions;
import io.ashdavies.commons.view.AbstractView;
import java.lang.ref.WeakReference;

public abstract class AbstractViewPresenter<View extends AbstractView> implements AbstractView, ViewPresenter<View> {

  private WeakReference<View> reference;

  @Override
  public void onAttach(@NonNull View view) {
    reference = new WeakReference<>(view);
    onViewAttached(reference.get());
  }

  @SuppressWarnings("WeakerAccess")
  protected void onViewAttached(@NonNull View view) {
    /* no op */
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
    onViewDetached();
  }

  @SuppressWarnings("WeakerAccess")
  protected void onViewDetached() {
    /* no op */
  }

  @Override
  public Context getContext() {
    return Preconditions.notNull(getView(), "View not attached").getContext();
  }

  @Override
  public void showProgress() {
    Preconditions.require(getView(), new Preconditions.Action<View>() {
      @Override
      public void run(View view) {
        view.showProgress();
      }
    });
  }

  @Override
  public void hideProgress() {
    Preconditions.require(getView(), new Preconditions.Action<View>() {
      @Override
      public void run(View view) {
        view.hideProgress();
      }
    });
  }

  @Override
  public void onError(@NonNull final Throwable throwable) {
    Preconditions.require(getView(), new Preconditions.Action<View>() {
      @Override
      public void run(View view) {
        view.onError(throwable);
        view.hideProgress();
      }
    });
  }
}
