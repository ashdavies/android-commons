package io.ashdavies.commons.view;

public interface AbstractView extends ProgressView {

  void onError(Throwable throwable);
}
