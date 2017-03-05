package io.ashdavies.rx.repository;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface Repository<T, Id> {

  Single<T> get(Id id) throws IndexNotFoundException;

  Flowable<T> getAll();

  Completable put(T t, Resolver<T, Id> resolver);

  interface Resolver<T, Id> {

    Id resolve(T t);
  }

  class IndexNotFoundException extends RuntimeException {
  }
}
