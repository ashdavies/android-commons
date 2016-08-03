package io.ashdavies.commons.rx;

import rx.Observer;

public class NonCompletingObserver<T> implements Observer<T> {
  private final Observer<T> observer;

  public NonCompletingObserver(Observer<T> observer) {
    this.observer = observer;
  }

  @Override
  public void onNext(T t) {
    observer.onNext(t);
  }

  @Override
  public void onError(Throwable exception) {
    observer.onError(exception);
  }

  @Override
  public void onCompleted() {
    // Do nothing
  }
}
