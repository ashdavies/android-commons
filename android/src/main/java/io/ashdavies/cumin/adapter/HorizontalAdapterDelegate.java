package io.ashdavies.cumin.adapter;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class HorizontalAdapterDelegate<VH, T> implements AdapterDelegate<VH, T> {
  private static final float DEFAULT_WIDTH_SCALE = 1.0f;

  private final LayoutInflater inflater;

  public HorizontalAdapterDelegate(LayoutInflater inflater) {
    this.inflater = inflater;
  }

  protected View onCreateView(ViewGroup parent) {
    View view = inflater.inflate(getLayoutId(), parent, false);

    ViewGroup.LayoutParams params = view.getLayoutParams();
    if (params.width == ViewGroup.LayoutParams.MATCH_PARENT) {
      params.width = (int) ((float) getWidth(parent) * getWidthScale());
      view.setLayoutParams(params);
    }

    return view;
  }

  @LayoutRes
  protected abstract int getLayoutId();

  protected float getWidthScale() {
    return DEFAULT_WIDTH_SCALE;
  }

  protected int getWidth(ViewGroup parent) {
    return parent.getWidth();
  }
}
