package io.ashdavies.commons.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListStorage<T> implements Storage<Integer, T> {
  private final List<T> list;

  public ListStorage() {
    this(new ArrayList<T>());
  }

  public ListStorage(List<T> list) {
    this.list = list;
  }

  @Override
  public T get(Integer index) throws IndexNotFoundException {
    return list.get(index);
  }

  @Override
  public Collection<T> getAll() {
    return new ArrayList<>(list);
  }

  @Override
  public void put(T t, Resolver<Integer, T> resolver) {
    list.set(resolver.resolve(t), t);
  }
}
