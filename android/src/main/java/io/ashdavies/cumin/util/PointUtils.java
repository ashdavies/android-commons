package io.ashdavies.cumin.util;

import android.graphics.Point;

public final class PointUtils {
  private PointUtils() {
    throw new RuntimeException("No instances");
  }

  public static Point add(Point a, Point b) {
    return new Point(a.x + b.x, a.y + b.y);
  }

  public static Point subtract(Point a, Point b) {
    return new Point(a.x - b.x, a.y - b.y);
  }

  public static Point offset(int width, double angle) {
    double radians = Math.toRadians(angle);
    double x = width * Math.cos(radians);
    double y = width * Math.sin(radians);

    return new Point((int) x, (int) y);
  }
}
