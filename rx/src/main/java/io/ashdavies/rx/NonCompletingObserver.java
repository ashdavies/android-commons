package io.ashdavies.rx;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class NonCompletingObserver<T> implements Observer<T> {

  private final Observer<T> observer;

  public NonCompletingObserver(Observer<T> observer) {
    this.observer = observer;
  }

  @Override
  public void onSubscribe(Disposable disposable) {
    observer.onSubscribe(disposable);
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
  public void onComplete() {
    // Do nothing
  }
}
