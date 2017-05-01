package io.ashdavies.commons.storage;

import java.util.Collection;

public interface Storage<T> {

  Collection<T> read();

  boolean delete(T t);

  void clear();

  boolean contains(T t);
}
