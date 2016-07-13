package io.ashdavies.cumin.util;

import android.os.Bundle;

public final class BundleUtils {
    private BundleUtils() {
        throw new IllegalStateException("No instances");
    }

    public static Bundle create(String key, String value) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        return bundle;
    }
}
