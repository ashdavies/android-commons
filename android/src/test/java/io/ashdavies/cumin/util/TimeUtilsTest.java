package io.ashdavies.cumin.util;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimeUtilsTest {
    private static final long NOW = System.currentTimeMillis();
    private static final long DAY = TimeUnit.DAYS.toMillis(1);
    private static final long WEEK = TimeUnit.DAYS.toMillis(7);

    @Test
    public void assertDaysBetweenNotBelowZero() {
        assertEquals(0, TimeUtils.daysBetween(NOW, NOW - WEEK));
    }

    @Test
    public void assertOneWeekBetween() {
        assertEquals(7, TimeUtils.daysBetween(NOW, NOW + WEEK));
    }

    @Test
    public void assertPercentageFinished() {
        assertEquals(0, TimeUtils.getRemainingPercentage(NOW - WEEK, NOW));
    }

    @Test
    public void assertHalfPercentage() {
        assertEquals(50, TimeUtils.getRemainingPercentage(NOW - WEEK, NOW + WEEK));
    }

    @Test
    public void assertThreeQuarterPercentage() {
        assertEquals(75, TimeUtils.getRemainingPercentage(NOW - WEEK, NOW + (WEEK * 3)));
    }

    @Test
    public void assertFullPercentage() {
        assertEquals(100, TimeUtils.getRemainingPercentage(NOW, NOW + WEEK));
    }

    @Test
    public void assertStartOfDayUtc() {
        assertEquals(0, TimeUtils.startOfDay(NOW, TimeZone.getTimeZone("UTC")) % DAY);
    }

    @Test
    public void assertStartOfDayDefaultLocale() {
        assertEquals(0, (TimeUtils.startOfDay(NOW) + TimeZone.getDefault().getOffset(NOW)) % DAY);
    }

    @Test
    public void assertIsToday() {
        assertEquals(true, TimeUtils.isToday(NOW));
    }

    @Test
    public void assertIsNotToday() {
        assertEquals(false, TimeUtils.isToday(NOW - DAY));
    }

    @Test
    public void assertWasYesterday() {
        assertEquals(true, TimeUtils.wasYesterday(NOW - DAY));
    }

    @Test
    public void assertIsNotYesterday() {
        assertEquals(false, TimeUtils.wasYesterday(NOW));
    }
}
