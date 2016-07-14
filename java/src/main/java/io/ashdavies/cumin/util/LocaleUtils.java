package io.ashdavies.cumin.util;

import java.util.Locale;

public final class LocaleUtils {
  private LocaleUtils() {}

  public static Locale toLocale(String str) {
    if (str == null) {
      return null;
    }
    if (str.isEmpty()) { // LANG-941 - JDK 8 introduced an empty locale where all fields are blank
      return new Locale("", "");
    }
    if (str.contains("#")) { // LANG-879 - Cannot handle Java 7 script & extensions
      throw new IllegalArgumentException("Invalid locale format: " + str);
    }
    int len = str.length();
    if (len < 2) {
      throw new IllegalArgumentException("Invalid locale format: " + str);
    }
    char ch0 = str.charAt(0);
    if (ch0 == '_') {
      if (len < 3) {
        throw new IllegalArgumentException("Invalid locale format: " + str);
      }
      if (len == 3) {
        return new Locale("", str.substring(1, 3));
      }
      if (len < 5) {
        throw new IllegalArgumentException("Invalid locale format: " + str);
      }
      if (str.charAt(3) != '_') {
        throw new IllegalArgumentException("Invalid locale format: " + str);
      }
      return new Locale("", str.substring(1, 3), str.substring(4));
    }

    String[] split = str.split("_", -1);
    int occurrences = split.length - 1;
    switch (occurrences) {
      case 0:
        if (len == 2 || len == 3) {
          return new Locale(str);
        }
        throw new IllegalArgumentException("Invalid locale format: " + str);

      case 1:
        if ((split[0].length() == 2 || split[0].length() == 3) && split[1].length() == 2) {
          return new Locale(split[0].toLowerCase(), split[1].toUpperCase());
        }
        throw new IllegalArgumentException("Invalid locale format: " + str);

      case 2:
        if ((split[0].length() == 2 || split[0].length() == 3)
            && (split[1].length() == 0 || (split[1].length() == 2))
            && split[2].length() > 0) {
          return new Locale(split[0].toLowerCase(), split[1].toUpperCase(), split[2]);
        }

        //$FALL-THROUGH$
      default:
        throw new IllegalArgumentException("Invalid locale format: " + str);
    }
  }
}
