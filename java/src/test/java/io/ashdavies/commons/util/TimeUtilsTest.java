package io.ashdavies.commons.util;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@RunWith(MockitoJUnitRunner.class)
public class TimeUtilsTest {
  private static final long NOW = System.currentTimeMillis();
  private static final long DAY = TimeUnit.DAYS.toMillis(1);
  private static final long WEEK = TimeUnit.DAYS.toMillis(7);

  @Test
  public void assertDaysBetweenNotBelowZero() {
    Assert.assertEquals(0, TimeUtils.daysBetween(NOW, NOW - WEEK));
  }

  @Test
  public void assertOneWeekBetween() {
    Assert.assertEquals(7, TimeUtils.daysBetween(NOW, NOW + WEEK));
  }

  @Test
  public void assertStartOfDayUtc() {
    Assert.assertEquals(0, TimeUtils.startOfDay(NOW, TimeZone.getTimeZone("UTC")) % DAY);
  }

  @Test
  public void assertStartOfDayDefaultLocale() {
    Assert.assertEquals(
        0, (TimeUtils.startOfDay(NOW) + TimeZone.getDefault().getOffset(NOW)) % DAY);
  }

  @Test
  public void assertIsToday() {
    Assert.assertEquals(true, TimeUtils.isToday(NOW));
  }

  @Test
  public void assertIsNotToday() {
    Assert.assertEquals(false, TimeUtils.isToday(NOW - DAY));
  }

  @Test
  public void assertWasYesterday() {
    Assert.assertEquals(true, TimeUtils.wasYesterday(NOW - DAY));
  }

  @Test
  public void assertIsNotYesterday() {
    Assert.assertEquals(false, TimeUtils.wasYesterday(NOW));
  }
}
