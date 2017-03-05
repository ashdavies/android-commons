package io.ashdavies.commons.util;

import android.graphics.Point;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class PointUtilsTest {

  @Test
  public void shouldHavePrivateConstructor() {
    assertThat(PointUtils.class.getConstructors().length).isEqualTo(0);
  }

  @Test
  public void shouldCombine() {
    Point a = new Point(5, 10);
    Point b = new Point(10, 15);

    assertThat(PointUtils.add(a, b)).isEqualTo(new Point(15, 25));
  }

  @Test
  public void shouldSubtract() {
    Point a = new Point(5, 10);
    Point b = new Point(10, 15);

    assertThat(PointUtils.subtract(a, b)).isEqualTo(new Point(-5, -5));
  }

  @Test
  public void shouldOffset() {
    double radians = Math.toRadians(90);
    int x = (int) Math.cos(radians);
    int y = (int) Math.sin(radians);

    assertThat(PointUtils.offset(5, 90)).isEqualTo(new Point(5 * x, 5 * y));
  }
}
