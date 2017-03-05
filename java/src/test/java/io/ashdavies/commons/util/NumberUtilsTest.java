package io.ashdavies.commons.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class NumberUtilsTest {

  private static final float DELTA = 1e-15F;

  @Test
  public void shouldLimitUpperBounds() {
    assertThat(NumberUtils.limit(15, 10)).isWithin(DELTA).of(10);
  }

  @Test
  public void shouldLimitLowerBounds() {
    assertThat(NumberUtils.limit(5, 10, 15)).isWithin(DELTA).of(10);
  }
}
