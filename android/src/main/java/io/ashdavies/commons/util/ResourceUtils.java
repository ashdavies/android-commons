package io.ashdavies.commons.util;

import android.content.Context;
import android.support.annotation.DimenRes;

public final class ResourceUtils {

  private ResourceUtils() {
    throw new IllegalStateException("No instances");
  }

  public static int getDimensionPixelSize(Context context, @DimenRes int dimenRes) {
    return context.getResources().getDimensionPixelSize(dimenRes);
  }
}
