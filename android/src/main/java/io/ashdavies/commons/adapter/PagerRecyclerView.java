package io.ashdavies.commons.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class PagerRecyclerView extends RecyclerView {
  public PagerRecyclerView(Context context) {
    super(context);
  }

  public PagerRecyclerView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public PagerRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  public boolean fling(int velocityX, int velocityY) {
    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
    if (linearLayoutManager == null) {
      throw new IllegalStateException("Cannot use PagerRecyclerView without LinearLayoutManager");
    }

    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
    int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

    View firstVisibleItem = linearLayoutManager.findViewByPosition(firstVisibleItemPosition);
    View lastVisibleItem = linearLayoutManager.findViewByPosition(lastVisibleItemPosition);

    if (firstVisibleItem == null || lastVisibleItem == null) {
      return false;
    }

    int leftMargin = (getWidth() - lastVisibleItem.getWidth()) / 2;
    int leftEdge = lastVisibleItem.getLeft();
    int rightMargin = (getWidth() - firstVisibleItem.getWidth()) / 2 + firstVisibleItem.getWidth();
    int rightEdge = firstVisibleItem.getRight();

    int scrollDistanceLeft = leftEdge - leftMargin;
    int scrollDistanceRight = rightMargin - rightEdge;

    if (velocityX > 0) {
      smoothScrollBy(scrollDistanceLeft, 0);
    } else {
      smoothScrollBy(-scrollDistanceRight, 0);
    }

    return true;
  }
}
