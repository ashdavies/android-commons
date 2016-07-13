package io.ashdavies.cumin.util;

public class NumberUtilsTest {
    private static final double DELTA = 1e-15;

    @Test
    public void assertLimitUpperBounds() {
        assertEquals(10, NumberUtils.limit(15, 10), DELTA);
    }

    @Test
    public void assertLimitLowerBounds() {
        assertEquals(10, NumberUtils.limit(5, 10, 15), DELTA);
    }
}
