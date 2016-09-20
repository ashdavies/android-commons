package io.ashdavies.commons.util;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.DimenRes;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public final class AnimationUtils {

  private static final long DEFAULT_DURATION = 300L;

  private AnimationUtils() {
    throw new IllegalStateException("No instances");
  }

  public static void animateWidth(Context context, View view, @DimenRes int resId) {
    animateWidth(view, context.getResources().getDimensionPixelOffset(resId));
  }

  public static void animateMeasuredWidth(View view, int height) {
    view.measure(view.getLayoutParams().width, height);
    animateWidth(view, view.getMeasuredHeight());
  }

  public static void animateWidth(View view, int height) {
    ValueAnimator animator = ValueAnimator.ofInt(view.getHeight(), height);
    animator.addUpdateListener(value -> ViewUtils.setWidth(view, (int) value.getAnimatedValue()));
    animator.setInterpolator(new AccelerateDecelerateInterpolator());
    animator.setDuration(DEFAULT_DURATION);

    animator.start();
  }

  public static void animateHeight(Context context, View view, @DimenRes int resId) {
    animateHeight(view, context.getResources().getDimensionPixelOffset(resId));
  }

  public static void animateMeasuredHeight(View view, int height) {
    view.measure(view.getLayoutParams().width, height);
    animateHeight(view, view.getMeasuredHeight());
  }

  public static void animateHeight(View view, int height) {
    ValueAnimator animator = ValueAnimator.ofInt(view.getHeight(), height);
    animator.addUpdateListener(value -> ViewUtils.setHeight(view, (int) value.getAnimatedValue()));
    animator.setInterpolator(new AccelerateDecelerateInterpolator());
    animator.setDuration(DEFAULT_DURATION);

    animator.start();
  }
}
