package io.ashdavies.cumin.factories;

import android.content.Context;

public interface InstanceFactory<T> {
    T newInstance(Context context);
}
