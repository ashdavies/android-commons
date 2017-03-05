package io.ashdavies.commons.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StringUtilsTest {

  @Test
  public void shouldJoinNull() {
    assertThat(StringUtils.join(null, ",", 0, 0)).isNull();
  }

  @Test
  public void shouldJoinNullEmpty() {
    assertThat(StringUtils.join(new String[] {}, ",", 0, 0)).isEqualTo(StringUtils.EMPTY);
  }

  @Test
  public void shouldPartialJoin() {
    assertThat(StringUtils.join(new String[] { "a", "b", "c", "d", "e" }, ",", 0, 3)).isEqualTo("a,b,c");
  }

  @Test
  public void shouldCompleteJoin() {
    assertThat(StringUtils.join(new String[] { "a", "b", "c", "d", "e" }, ",")).isEqualTo("a,b,c,d,e");
  }

  @Test
  public void shouldSplitEqual() {
    for (String item : StringUtils.split("123456789", 3)) {
      assertThat(item.length()).isEqualTo(3);
    }
  }

  @Test
  public void shouldSplitUnequal() {
    for (String item : StringUtils.split("qwertzuiop", 4)) {
      assertThat(item.length()).isLessThan(5);
    }
  }
}
