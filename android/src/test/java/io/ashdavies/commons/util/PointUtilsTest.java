package io.ashdavies.commons.util;

import android.graphics.Point;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Constructor;

import io.ashdavies.commons.ApplicationTestRunner;

import static junit.framework.Assert.assertEquals;

@RunWith(ApplicationTestRunner.class)
public class PointUtilsTest {

  @Test
  public void assertPrivateConstructor() {
    Constructor[] constructors = PointUtils.class.getConstructors();
    assertEquals(0, constructors.length);
  }

  @Test
  public void assertCombine() {
    Point a = new Point(5, 10);
    Point b = new Point(10, 15);

    Point combined = PointUtils.add(a, b);
    assertEquals(15, combined.x);
    assertEquals(25, combined.y);
  }

  @Test
  public void assertSubtract() {
    Point a = new Point(5, 10);
    Point b = new Point(10, 15);

    Point subtracted = PointUtils.subtract(a, b);
    assertEquals(-5, subtracted.x);
    assertEquals(-5, subtracted.y);
  }

  @Test
  public void assertOffset() {
    double radians = Math.toRadians(90);
    int x = (int) Math.cos(radians);
    int y = (int) Math.sin(radians);

    Point expected = new Point(5 * x, 5 * y);
    assertEquals(expected, PointUtils.offset(5, 90));
  }
}
