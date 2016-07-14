package io.ashdavies.cumin.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class StringUtilsTest {

    @Test
    public void assertJoinNull() {
        Assert.assertEquals(null, StringUtils.join(null, ",", 0, 0));
    }

    @Test
    public void assertJoinNullEmpty() {
        Assert.assertEquals(StringUtils.EMPTY, StringUtils.join(new String[]{}, ",", 0, 0));
    }

    @Test
    public void assertPartialJoin() {
        String[] array = new String[] { "a", "b", "c", "d", "e" };
        Assert.assertEquals("a,b,c", StringUtils.join(array, ",", 0, 3));
    }

    @Test
    public void assertCompleteJoin() {
        String[] array = new String[] { "a", "b", "c", "d", "e" };
        Assert.assertEquals("a,b,c,d,e", StringUtils.join(array, ","));
    }

    @Test
    public void assertSplitEqual() {
        for (String item : StringUtils.split("123456789", 3)) {
            assertEquals(3, item.length());
        }
    }

    @Test
    public void assertSplitUnequal() {
        for (String item : StringUtils.split("qwertzuiop", 4)) {
            assertTrue(item.length() <= 4);
        }
    }
}
