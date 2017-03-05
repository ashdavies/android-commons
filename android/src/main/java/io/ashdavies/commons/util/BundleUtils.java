package io.ashdavies.commons.util;

import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;

public final class BundleUtils {

  private BundleUtils() {
    throw new IllegalStateException("No instances");
  }

  public static Bundle create(String key, boolean value) {
    return builder().put(key, value).build();
  }

  public static Bundle create(String key, byte value) {
    return builder().put(key, value).build();
  }

  public static Bundle create(String key, char value) {
    return builder().put(key, value).build();
  }

  public static Bundle create(String key, short value) {
    return builder().put(key, value).build();
  }

  public static Bundle create(String key, int value) {
    return builder().put(key, value).build();
  }

  public static Bundle create(String key, long value) {
    return builder().put(key, value).build();
  }

  public static Bundle create(String key, float value) {
    return builder().put(key, value).build();
  }

  public static Bundle create(String key, double value) {
    return builder().put(key, value).build();
  }

  public static Bundle create(String key, String value) {
    return builder().put(key, value).build();
  }

  public static Bundle create(String key, CharSequence value) {
    return builder().put(key, value).build();
  }

  public static Bundle create(String key, Serializable value) {
    return builder().put(key, value).build();
  }

  public static Bundle create(String key, Parcelable value) {
    return builder().put(key, value).build();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private final Bundle bundle;

    public Builder() {
      this(new Bundle());
    }

    public Builder(Bundle bundle) {
      this.bundle = bundle;
    }

    public Builder put(String key, boolean value) {
      bundle.putBoolean(key, value);
      return this;
    }

    public Builder put(String key, byte value) {
      bundle.putByte(key, value);
      return this;
    }

    public Builder put(String key, char value) {
      bundle.putChar(key, value);
      return this;
    }

    public Builder put(String key, short value) {
      bundle.putShort(key, value);
      return this;
    }

    public Builder put(String key, int value) {
      bundle.putInt(key, value);
      return this;
    }

    public Builder put(String key, long value) {
      bundle.putLong(key, value);
      return this;
    }

    public Builder put(String key, float value) {
      bundle.putFloat(key, value);
      return this;
    }

    public Builder put(String key, double value) {
      bundle.putDouble(key, value);
      return this;
    }

    public Builder put(String key, String value) {
      bundle.putString(key, value);
      return this;
    }

    public Builder put(String key, CharSequence value) {
      bundle.putCharSequence(key, value);
      return this;
    }

    public Builder put(String key, Serializable value) {
      bundle.putSerializable(key, value);
      return this;
    }

    public Builder put(String key, Parcelable value) {
      bundle.putParcelable(key, value);
      return this;
    }

    public Bundle build() {
      return new Bundle(bundle);
    }
  }
}
