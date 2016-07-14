package io.ashdavies.cumin.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class AssetUtils {
    private static final String DEFAULT_CHARSET = "UTF-8";

    private AssetUtils() {
        throw new RuntimeException("No instances");
    }

    public static Drawable getDrawableAsset(Context context, String name) throws IOException {
        try {
            InputStream stream = context.getAssets().open(name);
            return Drawable.createFromStream(stream, null);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static String getStringAsset(Context context, String name) {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(name), DEFAULT_CHARSET));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }

        return result.toString();
    }
}
