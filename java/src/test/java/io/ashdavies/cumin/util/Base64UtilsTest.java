package io.ashdavies.cumin.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class Base64UtilsTest {
  private static final String BASE64_REGEX =
      "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$";

  @Test
  public void assertEncode() {
    String sample = "äbcdefghijklmnöpqrßtüvwxyz1234567890";
    String encoded = Base64Utils.encode(sample);

    assertTrue(encoded.matches(BASE64_REGEX));
  }
}
