package io.ashdavies.cumin.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

public final class AssetUtils {
    private AssetUtils() {
        throw new RuntimeException("No instances");
    }

    public static Drawable getAsset(Context context, String name) {
        try {
            InputStream stream = context.getAssets().open(name);
            return Drawable.createFromStream(stream, null);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
