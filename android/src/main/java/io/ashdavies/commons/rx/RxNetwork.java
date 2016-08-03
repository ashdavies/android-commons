package io.ashdavies.commons.rx;

import java.util.concurrent.TimeUnit;

import io.ashdavies.commons.view.BaseView;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

public final class RxNetwork {
  private RxNetwork() {
    throw new RuntimeException("No instances");
  }

  public static <T> Observable.Transformer<T, T> bind(final BaseView view) {
    return new Observable.Transformer<T, T>() {
      @Override
      public Observable<T> call(Observable<T> observable) {
        return observable
            .doOnSubscribe(
                new Action0() {
                  @Override
                  public void call() {
                    view.showProgress();
                  }
                })
            .doOnCompleted(
                new Action0() {
                  @Override
                  public void call() {
                    view.hideProgress();
                  }
                })
            .doOnError(
                new Action1<Throwable>() {
                  @Override
                  public void call(Throwable throwable) {
                    view.hideProgress();
                  }
                });
      }
    };
  }

  public static <T> Observable.Transformer<T, T> retry() {
    return new Observable.Transformer<T, T>() {
      @Override
      public Observable<T> call(Observable<T> observable) {
        return observable.retryWhen(
            new Func1<Observable<? extends Throwable>, Observable<?>>() {
              @Override
              public Observable<?> call(Observable<? extends Throwable> observable) {
                return observable
                    .zipWith(
                        Observable.range(1, 3),
                        new Func2<Throwable, Integer, Integer>() {
                          @Override
                          public Integer call(Throwable throwable, Integer integer) {
                            return integer;
                          }
                        })
                    .flatMap(
                        new Func1<Integer, Observable<?>>() {
                          @Override
                          public Observable<?> call(Integer integer) {
                            return Observable.timer((long) Math.pow(5, integer), TimeUnit.SECONDS);
                          }
                        });
              }
            });
      }
    };
  }
}
