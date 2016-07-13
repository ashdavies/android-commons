package io.ashdavies.cumin.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StringUtilsTest {

    @Test
    public void assertJoinNull() {
        assertEquals(null, StringUtils.join(null, ",", 0, 0));
    }

    @Test
    public void assertJoinNullEmpty() {
        assertEquals(StringUtils.EMPTY, StringUtils.join(new String[]{}, ",", 0, 0));
    }

    @Test
    public void assertPartialJoin() {
        String[] array = new String[] { "a", "b", "c", "d", "e" };
        assertEquals("a,b,c", StringUtils.join(array, ",", 0, 3));
    }

    @Test
    public void assertCompleteJoin() {
        String[] array = new String[] { "a", "b", "c", "d", "e" };
        assertEquals("a,b,c,d,e", StringUtils.join(array, ","));
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

    @Test
    public void assertIsEmpty() {
        assertEquals(true, StringUtils.isEmpty(null));
        assertEquals(true, StringUtils.isEmpty(""));
        assertEquals(false, StringUtils.isEmpty("hello"));
    }
}
