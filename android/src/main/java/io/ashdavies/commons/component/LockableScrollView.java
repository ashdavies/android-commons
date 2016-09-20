package io.ashdavies.commons.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class LockableScrollView extends ScrollView {

  private boolean scrollingEnabled = true;

  public LockableScrollView(Context context) {
    super(context);
  }

  public LockableScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public LockableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public LockableScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  public boolean isScrollingEnabled() {
    return scrollingEnabled;
  }

  public void setScrollingEnabled(boolean enabled) {
    scrollingEnabled = enabled;
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    switch (ev.getAction()) {
      case MotionEvent.ACTION_DOWN:
        return scrollingEnabled && super.onTouchEvent(ev);

      default:
        return super.onTouchEvent(ev);
    }
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return scrollingEnabled && super.onInterceptTouchEvent(ev);
  }
}

