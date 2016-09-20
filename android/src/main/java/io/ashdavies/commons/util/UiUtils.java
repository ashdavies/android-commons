package io.ashdavies.commons.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

public final class UiUtils {

  private UiUtils() {
    throw new IllegalStateException("No instances");
  }

  public static void showToast(Context context, String message) {
    makeToast(context, message).show();
  }

  public static void showToast(Context context, @StringRes int resId) {
    makeToast(context, resId).show();
  }

  public static Toast makeToast(Context context, String message) {
    return Toast.makeText(context, message, Toast.LENGTH_LONG);
  }

  public static Toast makeToast(Context context, @StringRes int resId) {
    return Toast.makeText(context, resId, Toast.LENGTH_LONG);
  }
}
