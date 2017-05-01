package io.ashdavies.commons.view;

public interface AbstractView extends BackpressView, LayoutProvider, ProgressView {

  void onError(Throwable throwable);
}
