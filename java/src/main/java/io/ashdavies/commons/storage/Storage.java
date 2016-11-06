package io.ashdavies.commons.storage;

import java.util.Collection;

public interface Storage<Id, T> {

  T get(Id id) throws IndexNotFoundException;

  Collection<T> getAll();

  void put(T t, Resolver<Id, T> resolver);

  class IndexNotFoundException extends RuntimeException {
  }

  interface Resolver<Id, T> {

    Id resolve(T t);
  }
}
