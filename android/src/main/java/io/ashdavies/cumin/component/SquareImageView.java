package io.ashdavies.cumin.component;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class SquareImageView extends AppCompatImageView {
  public SquareImageView(Context context) {
    super(context);
  }

  public SquareImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  @SuppressWarnings("SuspiciousNameCombination")
  protected void onMeasure(int width, int height) {
    super.onMeasure(width, height);

    int measuredWidth = getMeasuredWidth();
    int measuredHeight = getMeasuredHeight();

    if (measuredWidth > measuredHeight) {
      setMeasuredDimension(measuredHeight, measuredHeight);
    } else {
      setMeasuredDimension(measuredWidth, measuredWidth);
    }
  }
}
