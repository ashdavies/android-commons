package io.ashdavies.commons.util;

import android.os.Bundle;
import android.os.Parcelable;
import io.ashdavies.builders.BundleBuilder;
import java.io.Serializable;

public final class BundleUtils {

  private BundleUtils() {
    throw new IllegalStateException("No instances");
  }

  public static Bundle create(String key, boolean value) {
    return builder().putBoolean(key, value).build();
  }

  public static Bundle create(String key, byte value) {
    return builder().putByte(key, value).build();
  }

  public static Bundle create(String key, char value) {
    return builder().putChar(key, value).build();
  }

  public static Bundle create(String key, short value) {
    return builder().putShort(key, value).build();
  }

  public static Bundle create(String key, int value) {
    return builder().putInt(key, value).build();
  }

  public static Bundle create(String key, long value) {
    return builder().putLong(key, value).build();
  }

  public static Bundle create(String key, float value) {
    return builder().putFloat(key, value).build();
  }

  public static Bundle create(String key, double value) {
    return builder().putDouble(key, value).build();
  }

  public static Bundle create(String key, String value) {
    return builder().putString(key, value).build();
  }

  public static Bundle create(String key, CharSequence value) {
    return builder().putCharSequence(key, value).build();
  }

  public static Bundle create(String key, Serializable value) {
    return builder().putSerializable(key, value).build();
  }

  public static Bundle create(String key, Parcelable value) {
    return builder().putParcelable(key, value).build();
  }

  public static BundleBuilder builder() {
    return new BundleBuilder();
  }
}
