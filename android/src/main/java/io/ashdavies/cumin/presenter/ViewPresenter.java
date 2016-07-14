package io.ashdavies.cumin.presenter;

import android.support.annotation.NonNull;

public interface ViewPresenter<View> {
  void onAttach(@NonNull View view);

  void onDetach();

  void onError(@NonNull Throwable throwable);
}
