package io.ashdavies.commons.util;

public final class LocationUtils {
  private LocationUtils() {
    throw new IllegalStateException("No instances");
  }

  private static final double LATITUDE_LOWER_RANGE = -90;
  private static final double LATITUDE_UPPER_RANGE = 90;

  private static final double LONGITUDE_LOWER_RANGE = -180;
  private static final double LONGITUDE_UPPER_RANGE = 180;

  public static boolean isLatitudeValid(double latitude) {
    return latitude >= LATITUDE_LOWER_RANGE && latitude <= LATITUDE_UPPER_RANGE;
  }

  public static boolean isLongitudeValid(double longitude) {
    return longitude >= LONGITUDE_LOWER_RANGE && longitude <= LONGITUDE_UPPER_RANGE;
  }
}
