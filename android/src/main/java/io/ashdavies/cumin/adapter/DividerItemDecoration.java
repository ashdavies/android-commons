package io.ashdavies.cumin.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
  private static final int[] ATTRS = new int[] {android.R.attr.listDivider};

  public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
  public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

  private final Drawable divider;
  private final int orientation;

  public DividerItemDecoration(Context context, int orientation) {
    this(orientation, getDividerFromAttributes(context));
  }

  public DividerItemDecoration(int orientation, Drawable divider) {
    this.divider = divider;
    this.orientation = orientation;
  }

  private static Drawable getDividerFromAttributes(Context context) {
    TypedArray attributes = context.obtainStyledAttributes(ATTRS);
    Drawable divider = attributes.getDrawable(0);
    attributes.recycle();
    return divider;
  }

  @Override
  public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
    if (orientation == VERTICAL_LIST) {
      drawVertical(canvas, parent);
    } else {
      drawHorizontal(canvas, parent);
    }
  }

  public void drawVertical(Canvas canvas, RecyclerView parent) {
    int left = parent.getPaddingLeft();
    int right = parent.getWidth() - parent.getPaddingRight();

    int lastIndex = getLastIndex(parent.getChildCount());
    for (int i = 0; i < lastIndex; i++) {
      View child = parent.getChildAt(i);
      RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

      int top = child.getBottom() + params.bottomMargin;
      int bottom = top + divider.getIntrinsicHeight();

      divider.setBounds(left, top, right, bottom);
      divider.draw(canvas);
    }
  }

  public void drawHorizontal(Canvas canvas, RecyclerView parent) {
    int top = parent.getPaddingTop();
    int bottom = parent.getHeight() - parent.getPaddingBottom();

    int lastIndex = getLastIndex(parent.getChildCount());
    for (int i = 0; i < lastIndex; i++) {
      View child = parent.getChildAt(i);
      RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

      int left = child.getRight() + params.rightMargin;
      int right = left + divider.getIntrinsicHeight();

      divider.setBounds(left, top, right, bottom);
      divider.draw(canvas);
    }
  }

  private static int getLastIndex(int childCount) {
    return childCount - 1;
  }

  @Override
  public void getItemOffsets(
      Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    if (orientation == VERTICAL_LIST) {
      outRect.set(0, 0, 0, divider.getIntrinsicHeight());
    } else {
      outRect.set(0, 0, divider.getIntrinsicWidth(), 0);
    }
  }
}
