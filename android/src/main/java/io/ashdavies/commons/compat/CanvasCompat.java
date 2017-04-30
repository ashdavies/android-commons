package io.ashdavies.commons.compat;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;

public final class CanvasCompat {

  private static final RectF rectF = new RectF();

  private CanvasCompat() {
    throw new IllegalStateException("No instances");
  }

  public static void drawRoundRect(Canvas canvas, float left, float top, float right, float bottom, float rx, float ry, Paint paint) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      CanvasCompatApi21.drawRoundRect(canvas, left, top, right, bottom, rx, ry, paint);
    } else {
      rectF.set(left, top, right, bottom);
      canvas.drawRoundRect(rectF, rx, ry, paint);
    }
  }
}
