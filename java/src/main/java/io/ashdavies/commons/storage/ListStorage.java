package io.ashdavies.commons.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

public class ListStorage<T> implements IndexedStorage<T>, Iterable<T> {

  private final List<T> ts;

  public ListStorage() {
    this.ts = new ArrayList<>();
  }

  public ListStorage(List<T> ts) {
    this.ts = new ArrayList<>(ts);
  }

  @Override
  public T get(Predicate<T> predicate) throws IndexNotFoundException {
    for (T t : ts) {
      if (predicate.test(t)) {
        return t;
      }
    }

    throw new IndexNotFoundException();
  }

  @Override
  public Collection<T> read() {
    return ts;
  }

  @Override
  public boolean delete(T t) {
    return ts.remove(t);
  }

  @Override
  public void clear() {
    ts.clear();
  }

  @Override
  public boolean contains(T t) {
    return ts.contains(t);
  }

  @Nonnull
  @Override
  public Iterator<T> iterator() {
    return ts.iterator();
  }
}
