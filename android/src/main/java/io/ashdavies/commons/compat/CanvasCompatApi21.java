package io.ashdavies.commons.compat;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class CanvasCompatApi21 {

  private CanvasCompatApi21() {
    throw new IllegalStateException("No instances");
  }

  public static void drawRoundRect(Canvas canvas, float left, float top, float right, float bottom, float rx, float ry, Paint paint) {
    canvas.drawRoundRect(left, top, right, bottom, rx, ry, paint);
  }
}
