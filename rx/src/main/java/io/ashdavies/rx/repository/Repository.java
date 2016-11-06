package io.ashdavies.rx.repository;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface Repository<T, Id> {

  Flowable<T> get(Id id) throws IndexNotFoundException;

  Flowable<T> getAll();

  Completable put(T t, Resolver<T, Id> resolver);

  interface Resolver<T, Id> {

    Id resolve(T t);
  }

  public class IndexNotFoundException extends RuntimeException {
  }
}
