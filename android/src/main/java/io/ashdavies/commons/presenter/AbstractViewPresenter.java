package io.ashdavies.commons.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import io.ashdavies.commons.view.AbstractView;
import java.lang.ref.WeakReference;

public abstract class AbstractViewPresenter<View extends AbstractView> implements ViewPresenter<View> {

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
  public void onError(@NonNull Throwable throwable) {
    View view = getView();
    if (view != null) {
      view.onError(throwable);
    }
  }
}
