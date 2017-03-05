package io.ashdavies.commons.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class Base64UtilsTest {

  private static final String BASE64_REGEX = "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$";

  @Test
  public void shouldEncode() {
    assertThat(Base64Utils.encode("äbcdefghijklmnöpqrßtüvwxyz1234567890").matches(BASE64_REGEX)).isTrue();
  }
}
