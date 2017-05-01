package io.ashdavies.commons.util;

public final class NumberUtils {

  private NumberUtils() {
    throw new IllegalStateException("No instances");
  }

  public static float limit(double amount, double upper) {
    return limit(amount, 0, upper);
  }

  public static float limit(double amount, double lower, double upper) {
    return (float) Math.max(Math.min(amount, upper), lower);
  }

  public static int max(int... values) {
    int max = Integer.MIN_VALUE;

    for (int value : values) {
      if (value > max) {
        max = value;
      }
    }

    return max;
  }

  public static double max(double... values) {
    double max = Double.NEGATIVE_INFINITY;

    for (double value : values) {
      if (value > max) {
        max = value;
      }
    }

    return max;
  }
}
