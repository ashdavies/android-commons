package io.ashdavies.commons.presenter;

import android.support.annotation.Nullable;
import io.ashdavies.commons.view.AbstractView;

public abstract class AbstractViewPresenter<View extends AbstractView> implements AbstractView, ViewPresenter<View> {

  @Nullable
  private View view;

  @Override
  public void onAttach(View view) {
    this.view = view;
    onViewAttached();
  }

  abstract void onViewAttached();

  protected View getView() {
    if (view == null) {
      throw new ViewNotAttachedException();
    }

    return view;
  }

  protected boolean isAttached() {
    return view != null;
  }

  @Override
  public void onDetach() {
    this.view = null;
    onViewDetached();
  }

  abstract void onViewDetached();

  @Override
  public void onProgress(boolean progressing) {
    getView().onProgress(progressing);
  }

  @Override
  public void onError(Throwable throwable) {
    getView().onError(throwable);
  }

  private static class ViewNotAttachedException extends RuntimeException {
  }
}
