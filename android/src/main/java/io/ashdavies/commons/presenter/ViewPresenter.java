package io.ashdavies.commons.presenter;

public interface ViewPresenter<View> {

  void onAttach(View view);

  void onDetach();

  void onError(Throwable throwable);
}
