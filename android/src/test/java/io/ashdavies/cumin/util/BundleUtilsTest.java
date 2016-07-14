package io.ashdavies.cumin.util;

import android.os.Bundle;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.ashdavies.cumin.ApplicationTestRunner;

import static junit.framework.Assert.assertEquals;

@RunWith(ApplicationTestRunner.class)
public class BundleUtilsTest {

    @Test
    public void assertCreateWithStringValue() {
        Bundle bundle = BundleUtils.create("key", "value");
        assertEquals("value", bundle.getString("key"));
    }

    @Test
    public void assertCreateWithParcelableValue() {
        Bundle parcel = BundleUtils.create("key", "value");
        Bundle bundle = BundleUtils.create("parcel", parcel);

        assertEquals(parcel, bundle.getParcelable("parcel"));
    }

    @Test
    public void assertBuilderCreateWithStringValue() {
        Bundle bundle = BundleUtils.builder()
                .put("key", "value")
                .build();

        assertEquals("value", bundle.getString("key"));
    }

    @Test
    public void assertBuilderCreateWithLongValue() {
        Bundle bundle = BundleUtils.builder()
                .put("key", 123L)
                .build();

        assertEquals(123L, bundle.getLong("key"));
    }

    @Test
    public void assertBuilderCreateWithFloatValue() {
        Bundle bundle = BundleUtils.builder()
                .put("key", 123F)
                .build();

        assertEquals(123F, bundle.getFloat("key"));
    }
}
