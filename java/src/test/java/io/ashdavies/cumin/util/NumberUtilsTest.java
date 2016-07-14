package io.ashdavies.cumin.util;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class NumberUtilsTest {
    private static final double DELTA = 1e-15;

    @Test
    public void assertLimitUpperBounds() {
        Assert.assertEquals(10, NumberUtils.limit(15, 10), DELTA);
    }

    @Test
    public void assertLimitLowerBounds() {
        Assert.assertEquals(10, NumberUtils.limit(5, 10, 15), DELTA);
    }
}
