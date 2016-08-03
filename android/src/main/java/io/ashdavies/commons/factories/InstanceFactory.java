package io.ashdavies.commons.factories;

import android.content.Context;

public interface InstanceFactory<T> {
  T newInstance(Context context);
}
