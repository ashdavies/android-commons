package io.ashdavies.commons.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class StringUtils {
  public static final String EMPTY = "";
  public static final String SPACE = " ";

  private StringUtils() {
    throw new IllegalStateException("No instances");
  }

  public static List<String> stringify(List<?> objects) {
    List<String> list = new ArrayList<>();

    for (Object object : objects) {
      list.add(object.toString());
    }

    return list;
  }

  public static String join(Collection<CharSequence> list, CharSequence separator) {
    return join(list.toArray(new CharSequence[list.size()]), separator);
  }

  public static String join(CharSequence[] array, CharSequence separator) {
    return join(array, separator, 0, array.length);
  }

  public static String join(
      CharSequence[] array, CharSequence separator, int startIndex, int endIndex) {
    if (array == null) {
      return null;
    }

    int noOfItems = endIndex - startIndex;
    if (noOfItems <= 0) {
      return EMPTY;
    }

    StringBuilder builder = new StringBuilder(noOfItems * 16);
    for (int i = startIndex; i < endIndex; i++) {
      if (i > startIndex) {
        builder.append(separator);
      }
      if (array[i] != null) {
        builder.append(array[i]);
      }
    }

    return builder.toString();
  }

  public static String[] split(String string, int size) {
    List<String> parts = new ArrayList<>();

    while (string.length() > size) {
      parts.add(string.substring(0, size));
      string = string.substring(size);
    }

    if (string.length() > 0) {
      parts.add(string);
    }

    return parts.toArray(new String[parts.size()]);
  }

  public static String toTitleCase(CharSequence input) {
    String[] words = input.toString().split(SPACE);

    for (int i = 0; i < words.length; i++) {
      words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
    }

    return join(words, SPACE);
  }
}
