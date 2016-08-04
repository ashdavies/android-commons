package io.ashdavies.commons.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.annotation.Nullable;

public final class ViewUtils {
  private ViewUtils() {
    throw new IllegalStateException("No instances");
  }

  @SuppressWarnings("unchecked")
  public static <T extends View> T findViewById(View parent, @IdRes int id) {
    return (T) parent.findViewById(id);
  }

  @SuppressWarnings("unchecked")
  public static <T extends View> T findViewById(ViewGroup parent, @IdRes int id) {
    return (T) parent.findViewById(id);
  }

  @SuppressWarnings("unchecked")
  public static <T extends View> T findViewById(Activity activity, @IdRes int id) {
    return (T) activity.findViewById(id);
  }

  @Nullable
  @SuppressWarnings("unchecked")
  public static <T extends View> T findViewById(Fragment fragment, @IdRes int id) {
    View view = fragment.getView();
    if (view == null) {
      return null;
    }

    return (T) view.findViewById(id);
  }

  @SuppressWarnings("unchecked")
  public static <T extends ViewGroup.LayoutParams> T getLayoutParams(View view) {
    return (T) view.getLayoutParams();
  }

  @SuppressWarnings("unchecked")
  public static <T extends View> T inflate(Context context, @LayoutRes int layoutId) {
    return (T) LayoutInflater.from(context).inflate(layoutId, null, false);
  }

  public static <T extends View> T inflate(ViewGroup root, int layoutId) {
    return inflate(root, layoutId, false);
  }

  @SuppressWarnings("unchecked")
  private static <T extends View> T inflate(ViewGroup root, int layoutId, boolean attach) {
    return (T) LayoutInflater.from(root.getContext()).inflate(layoutId, root, attach);
  }
}
