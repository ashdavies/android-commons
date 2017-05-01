package io.ashdavies.commons.storage;

public interface IndexedStorage<T> extends Storage<T> {

  T get(Predicate<T> predicate) throws IndexNotFoundException;

  class IndexNotFoundException extends RuntimeException {
  }

  interface Predicate<T> {

    boolean test(T t);
  }
}
