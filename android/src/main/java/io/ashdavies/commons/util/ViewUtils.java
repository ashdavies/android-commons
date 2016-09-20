package io.ashdavies.commons.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DimenRes;
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

  public static <T extends View> T inflate(Context context, @LayoutRes int layoutId) {
    return inflate(context, layoutId, null, false);
  }

  public static <T extends View> T inflate(@LayoutRes int layoutId, ViewGroup root) {
    return inflate(layoutId, root, false);
  }

  public static <T extends View> T inflate(@LayoutRes int layoutId, ViewGroup root, boolean attachToRoot) {
    return inflate(root.getContext(), layoutId, root, attachToRoot);
  }

  @SuppressWarnings("unchecked")
  private static <T extends View> T inflate(Context context, @LayoutRes int layoutId, @Nullable ViewGroup root, boolean attachToRoot) {
    return (T) LayoutInflater.from(context).inflate(layoutId, root, attachToRoot);
  }

  public static void setWidth(Context context, View view, @DimenRes int resId) {
    setWidth(view, context.getResources().getDimensionPixelSize(resId));
  }

  public static void setWidth(View view, int px) {
    ViewGroup.LayoutParams params = view.getLayoutParams();
    params.width = px;

    view.setLayoutParams(params);
  }

  public static void setHeight(Context context, View view, @DimenRes int resId) {
    setHeight(view, context.getResources().getDimensionPixelSize(resId));
  }

  public static void setHeight(View view, int px) {
    ViewGroup.LayoutParams params = view.getLayoutParams();
    params.height = px;

    view.setLayoutParams(params);
  }
}
