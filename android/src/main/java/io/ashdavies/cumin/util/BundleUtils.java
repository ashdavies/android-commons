package io.ashdavies.cumin.util;

import android.os.Bundle;
import android.os.Parcelable;

public final class BundleUtils {
  private BundleUtils() {
    throw new IllegalStateException("No instances");
  }

  public static Bundle create(String key, String value) {
    return builder().put(key, value).build();
  }

  public static Bundle create(String key, int value) {
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

    public Builder put(String key, String value) {
      bundle.putString(key, value);
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

    public Builder put(String key, Parcelable value) {
      bundle.putParcelable(key, value);
      return this;
    }

    public Bundle build() {
      return new Bundle(bundle);
    }
  }
}
