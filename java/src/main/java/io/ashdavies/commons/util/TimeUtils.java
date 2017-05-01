package io.ashdavies.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public final class TimeUtils {

  public static final String YYYY_MM_DD_T_HH_MM_SS_SSSZ = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
  public static final String YYYY_MM_DD_T_HH_MM_SS_SSSSSSZ = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ";

  public static final String EEEE_DD_MMM_YYYY = "EEEE, dd MMM yyyy";
  public static final String EEEE = "EEEE";

  public static final String DD_MMM_YYYY = "dd MMM yyyy";
  public static final String DD_MM_YYYY = "dd/MM/yyyy";
  public static final String MM_YY = "MM/yy";

  public static final String HH_MM = "HH:mm";

  private TimeUtils() {
    throw new IllegalStateException("No instances");
  }

  public static SimpleDateFormat format(String format) {
    return new SimpleDateFormat(format, Locale.getDefault());
  }

  public static Calendar from(int year, int monthOfYear, int dayOfMonth) {
    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, monthOfYear);
    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

    return calendar;
  }

  public static Calendar from(int hourOfDay, int minute) {
    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
    calendar.set(Calendar.MINUTE, minute);

    return calendar;
  }

  public static Calendar nextDay(Calendar original) {
    Calendar calendar = (Calendar) original.clone();
    calendar.add(Calendar.DATE, 1);
    return calendar;
  }

  public static Calendar alter(Calendar calendar) {
    return alter(calendar, TimeZone.getDefault());
  }

  public static Calendar alter(Calendar calendar, TimeZone zone) {
    Calendar altered = (Calendar) calendar.clone();
    calendar.add(Calendar.MILLISECOND, zone.getRawOffset());
    return altered;
  }

  public static long parse(String string, String format) {
    return parse(string, format(format));
  }

  public static long parse(String string, SimpleDateFormat format) {
    try {
      return format.parse(string).getTime();
    } catch (ParseException exception) {
      throw new RuntimeException(exception);
    }
  }

  public static boolean isToday(long millis) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(millis);

    return isSameDay(calendar, Calendar.getInstance());
  }

  public static boolean isTomorrow(long millis) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(millis);

    Calendar tomorrow = Calendar.getInstance();
    tomorrow.add(Calendar.DATE, 1);

    return isSameDay(calendar, tomorrow);
  }

  public static boolean isSameDay(Calendar calendar, Calendar comparison) {
    Calendar tomorrow = Calendar.getInstance();
    tomorrow.add(Calendar.DATE, 1);

    return comparison.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
        && comparison.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR);
  }

  public static boolean wasYesterday(long millis) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(millis);

    return wasYesterday(calendar);
  }

  public static boolean wasYesterday(Calendar calendar) {
    Calendar yesterday = Calendar.getInstance();
    yesterday.add(Calendar.DATE, -1);

    return yesterday.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
        && yesterday.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR);
  }

  public static boolean isWithin(long millis, long limit) {
    return System.currentTimeMillis() - millis < limit;
  }

  public static double minutesRemaining(long millis) {
    return Math.floor((System.currentTimeMillis() - millis) / TimeUnit.MINUTES.toMillis(1));
  }

  public static double hoursRemaining(long millis) {
    return Math.floor((System.currentTimeMillis() - millis) / TimeUnit.HOURS.toMillis(1));
  }

  public static long daysBetween(long from, long to) {
    if (from > to) {
      return 0;
    }

    return (long) Math.ceil((to - from) / TimeUnit.DAYS.toMillis(1));
  }

  public static long startOfDay(long millis) {
    return startOfDay(millis, TimeZone.getDefault());
  }

  public static long startOfDay(long millis, TimeZone zone) {
    Calendar calendar = Calendar.getInstance(zone);
    calendar.setTimeInMillis(millis);

    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    return calendar.getTimeInMillis();
  }
}
