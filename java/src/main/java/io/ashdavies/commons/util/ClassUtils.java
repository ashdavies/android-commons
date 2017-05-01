package io.ashdavies.commons.util;

public final class ClassUtils {

  private ClassUtils() {
    throw new IllegalStateException("No instances");
  }

  @SuppressWarnings("unchecked")
  public static <T> T cast(Object object) {
    try {
      return (T) object;
    } catch (ClassCastException exception) {
      throw new RuntimeException(object.toString() + " cannot be cast to wanted class", exception);
    }
  }
}
