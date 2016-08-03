package io.ashdavies.commons.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class PagerItemDecoration extends RecyclerView.ItemDecoration {
  private final float defaultOffsetRatio;

  public PagerItemDecoration(float defaultOffsetRatio) {
    this.defaultOffsetRatio = defaultOffsetRatio;
  }

  @Override
  public void getItemOffsets(
      Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    if (isFirst(parent, view, state)) {
      outRect.set(getOffset(parent, view), 0, 0, 0);
    } else if (isLast(parent, view, state)) {
      outRect.set(0, 0, getOffset(parent, view), 0);
    } else {
      outRect.set(0, 0, 0, 0);
    }
  }

  protected boolean isFirst(RecyclerView parent, View child, RecyclerView.State state) {
    return parent.getChildLayoutPosition(child) == 0 && state.getItemCount() > 0;
  }

  protected boolean isLast(RecyclerView parent, View child, RecyclerView.State state) {
    return parent.getChildLayoutPosition(child) == state.getItemCount() - 1;
  }

  protected int getOffset(RecyclerView parent, View child) {
    if (child.getWidth() == 0) {
      return (int) (parent.getWidth() * defaultOffsetRatio);
    }

    return (parent.getWidth() - child.getWidth()) / 2;
  }
}
