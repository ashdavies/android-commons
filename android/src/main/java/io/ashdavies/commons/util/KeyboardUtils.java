package io.ashdavies.commons.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public final class KeyboardUtils {

  private KeyboardUtils() {
    throw new IllegalStateException("No instances");
  }

  public static void dismiss(Context context, View view) {
    getManager(context).hideSoftInputFromInputMethod(view.getWindowToken(), 0);
  }

  public static void dismiss(Activity activity) {
    dismiss(activity, activity.getCurrentFocus());
  }

  public static void dismiss(Context context, View... views) {
    if (views == null) {
      return;
    }

    InputMethodManager manager = getManager(context);
    for (View view : views) {
      if (view == null) {
        continue;
      }

      manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
      view.clearFocus();
    }
  }

  public static void show(Context context, View view) {
    if (view == null) {
      return;
    }

    view.requestFocus();
    getManager(context).showSoftInput(view, 0);
  }

  private static InputMethodManager getManager(Context context) {
    return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
  }
}
