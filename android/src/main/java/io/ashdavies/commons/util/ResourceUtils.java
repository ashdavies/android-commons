package io.ashdavies.commons.util;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.DimenRes;
import java.util.Random;

public final class ResourceUtils {

  private ResourceUtils() {
    throw new IllegalStateException("No instances");
  }

  public static int getDimensionPixelSize(Context context, @DimenRes int dimenRes) {
    return context.getResources().getDimensionPixelSize(dimenRes);
  }

  public static String getRandom(Context context, @ArrayRes int resId) {
    String[] array = context.getResources().getStringArray(resId);

    Random random = new Random();
    int index = random.nextInt(array.length);

    return array[index];
  }
}
