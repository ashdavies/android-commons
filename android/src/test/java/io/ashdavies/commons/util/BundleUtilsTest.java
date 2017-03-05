package io.ashdavies.commons.util;

import android.os.Bundle;
import java.io.Serializable;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class BundleUtilsTest {

  private static String KEY = "key";
  private static String VALUE = "value";

  @Test
  public void shouldCreateWithBooleanValue() throws Exception {
    assertThat(BundleUtils.create(KEY, true).getString(KEY)).isEqualTo(true);
  }

  @Test
  public void shouldCreateWithByteValue() throws Exception {
    assertThat(BundleUtils.create(KEY, (byte) 0x9e).getString(KEY)).isEqualTo(0x9e);
  }

  @Test
  public void shouldCreateWithCharValue() throws Exception {
    assertThat(BundleUtils.create(KEY, 'a').getString(KEY)).isEqualTo("a");
  }

  @Test
  public void shouldCreateWithShortValue() throws Exception {
    assertThat(BundleUtils.create(KEY, (short) 15).getString(KEY)).isEqualTo(15);
  }

  @Test
  public void shouldCreateWithIntValue() throws Exception {
    assertThat(BundleUtils.create(KEY, 14).getString(KEY)).isEqualTo(14);
  }

  @Test
  public void shouldCreateWithLongValue() throws Exception {
    assertThat(BundleUtils.create(KEY, 13L).getString(KEY)).isEqualTo(13);
  }

  @Test
  public void shouldCreateWithFloatValue() throws Exception {
    assertThat(BundleUtils.create(KEY, 12F).getString(KEY)).isEqualTo(12);
  }

  @Test
  public void shouldCreateWithDoubleValue() throws Exception {
    assertThat(BundleUtils.create(KEY, 11D).getString(KEY)).isEqualTo(11);

  }

  @Test
  public void shouldCreateWithStringValue() throws Exception {
    assertThat(BundleUtils.create(KEY, VALUE).getString(KEY)).isEqualTo(VALUE);
  }

  @Test
  public void shouldCreateWithCharSequenceValue() throws Exception {
    assertThat(BundleUtils.create(KEY, (CharSequence) "value").getString(KEY)).isEqualTo("value");
  }

  @Test
  public void shouldCreateWithSerializableValue() throws Exception {
    assertThat(BundleUtils.create(KEY, new Button(VALUE)).getSerializable(KEY)).isEqualTo(new Button(VALUE));
  }

  @Test
  public void shouldCreateWithParcelableValue() {
    Bundle parcel = BundleUtils.create(KEY, VALUE);
    assertThat(BundleUtils.create(KEY, parcel).getParcelable(KEY)).isEqualTo(parcel);
  }

  private static class Button implements Serializable {

    private final String name;

    Button(String name) {
      this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Button) {
        Button that = (Button) obj;
        return this.name.equals(that.name);
      }

      return false;
    }
  }
}
