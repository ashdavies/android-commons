package io.ashdavies.commons.util;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TimeUtilsTest {

  private static final long NOW = System.currentTimeMillis();
  private static final long DAY = TimeUnit.DAYS.toMillis(1);
  private static final long WEEK = TimeUnit.DAYS.toMillis(7);

  @Test
  public void shouldDaysBetweenNotBelowZero() {
    assertThat(TimeUtils.daysBetween(NOW, NOW - WEEK)).isEqualTo(0);
  }

  @Test
  public void shouldOneWeekBetween() {
    assertThat(TimeUtils.daysBetween(NOW, NOW + WEEK)).isEqualTo(7);
  }

  @Test
  public void shouldStartOfDayUtc() {
    assertThat(TimeUtils.startOfDay(NOW, TimeZone.getTimeZone("UTC")) % DAY).isEqualTo(0);
  }

  @Test
  public void shouldStartOfDayDefaultLocale() {
    assertThat((TimeUtils.startOfDay(NOW) + TimeZone.getDefault().getOffset(NOW)) % DAY).isEqualTo(0);
  }

  @Test
  public void shouldIsToday() {
    assertThat(TimeUtils.isToday(NOW)).isTrue();
  }

  @Test
  public void shouldIsNotToday() {
    assertThat(TimeUtils.isToday(NOW - DAY)).isFalse();
  }

  @Test
  public void shouldWasYesterday() {
    assertThat(TimeUtils.wasYesterday(NOW - DAY)).isTrue();
  }

  @Test
  public void shouldIsNotYesterday() {
    assertThat(TimeUtils.wasYesterday(NOW)).isFalse();
  }
}
