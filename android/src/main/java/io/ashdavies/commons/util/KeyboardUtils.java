package io.ashdavies.commons.util;

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

  private static InputMethodManager getManager(Context context) {
    return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
  }
}
