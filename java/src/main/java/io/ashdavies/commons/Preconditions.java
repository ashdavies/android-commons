package io.ashdavies.commons;

import java.util.Collection;
import java.util.Map;
import javax.annotation.Nullable;

public final class Preconditions {

  private Preconditions() {
  }

  public static CharSequence notEmpty(@Nullable CharSequence sequence, String message) {
    verify(sequence, new Assertion<CharSequence>() {
      @Override
      public boolean of(CharSequence value) {
        return !value.toString().isEmpty();
      }
    }, message);

    return sequence;
  }

  public static String notEmpty(@Nullable String string, String message) {
    verify(string, new Assertion<String>() {
      @Override
      public boolean of(String value) {
        return !value.isEmpty();
      }
    }, message);

    return string;
  }

  public static <T> Collection<T> notEmpty(@Nullable Collection<T> collection, String message) {
    verify(collection, new Assertion<Collection<T>>() {
      @Override
      public boolean of(Collection<T> value) {
        return !value.isEmpty();
      }
    }, message);

    return collection;
  }

  public static <K, V> Map<K, V> notEmpty(@Nullable Map<K, V> map, String message) {
    verify(map, new Assertion<Map<K, V>>() {
      @Override
      public boolean of(Map<K, V> value) {
        return !value.isEmpty();
      }
    }, message);

    return map;
  }

  public static <T> void verify(@Nullable T value, Assertion<T> invoker, String message) {
    verify(invoker.of(notNull(value, message)), message);
  }

  public static void verify(boolean assertion, String message) {
    if (!assertion) {
      throw new IllegalArgumentException(message);
    }
  }

  public static <T> T notNull(@Nullable T value, String message) {
    if (value == null) {
      throw new NullPointerException(message);
    }

    return value;
  }

  public static <T> T withDefault(@Nullable T value, T defaultValue) {
    if (value == null) {
      return defaultValue;
    }

    return value;
  }

  public interface Assertion<T> {

    boolean of(T value);
  }
}
