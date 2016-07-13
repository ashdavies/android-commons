package io.ashdavies.cumin.util;

import android.util.Base64;

import java.nio.charset.Charset;

public final class Base64Utils {
    private static final Charset CHARSET = Charset.forName("UTF-8");

    private Base64Utils() {
        throw new IllegalStateException("No instances");
    }

    public static String encode(String input) {
        return encode(input, CHARSET);
    }

    public static String encode(String input, Charset charset) {
        return encode(input.getBytes(charset));
    }

    public static String encode(byte[] bytes) {
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
