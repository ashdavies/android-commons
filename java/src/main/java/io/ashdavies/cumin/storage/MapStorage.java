package io.ashdavies.cumin.storage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapStorage<Id, T> implements Storage<Id, T> {
  private final Map<Id, T> storage;

  public MapStorage() {
    this(new HashMap<Id, T>());
  }

  public MapStorage(Map<Id, T> map) {
    this.storage = map;
  }

  @Override
  public T get(Id id) throws IndexNotFoundException {
    if (!storage.containsKey(id)) {
      throw new IndexNotFoundException();
    }

    return storage.get(id);
  }

  @Override
  public Collection<T> getAll() {
    return storage.values();
  }

  @Override
  public void set(Id id, T t) {
    storage.put(id, t);
  }
}
