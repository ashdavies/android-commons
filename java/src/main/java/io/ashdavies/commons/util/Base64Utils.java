package io.ashdavies.commons.util;

import java.nio.charset.Charset;
import okio.ByteString;

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
    return ByteString.of(bytes).base64();
  }

  public static byte[] decode(String input) {
    return ByteString.decodeBase64(input).toByteArray();
  }
}
