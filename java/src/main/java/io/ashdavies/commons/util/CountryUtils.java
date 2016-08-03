package io.ashdavies.commons.util;

public final class CountryUtils {
  private static final String AUSTRIA_ISO_3 = "AUT";
  private static final String GERMANY_ISO_3 = "DEU";

  private CountryUtils() {
    throw new IllegalStateException("No instances");
  }

  public static boolean isAustria(String country) {
    return AUSTRIA_ISO_3.equals(country);
  }

  public static boolean isGermany(String country) {
    return GERMANY_ISO_3.equals(country);
  }
}
