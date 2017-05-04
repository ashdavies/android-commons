package io.ashdavies.commons.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.annotation.Nullable;

public final class Collections {

  private Collections() {
    throw new IllegalStateException("No instances");
  }

  public static <T> Collection<T> intersect(Collection<? extends T> a, Collection<? extends T> b) {
    final Collection<T> result = new ArrayList<T>();
    for (final T t : a) {
      if (b.remove(t)) {
        result.add(t);
      }
    }
    return result;
  }

  public static boolean isEmpty(@Nullable Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  public static int size(@Nullable Collection<?> collection) {
    return isEmpty(collection) ? 0 : collection.size();
  }

  public static boolean isEmpty(@Nullable Map<?, ?> map) {
    return map == null || map.isEmpty();
  }

  public static <T> boolean isEmpty(T[] l) {
    return l == null || l.length < 1;
  }
}
