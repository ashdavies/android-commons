package io.ashdavies.commons.component;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class SimpleTextWatcher implements TextWatcher {

  public SimpleTextWatcher(OnTextChangedListener listener) {
    return new SimpleTextWatcher() {
      @Override
      public void onTextChanged(CharSequence sequence, int start, int before, int count) {
        listener.onTextChanged(sequence, start, before, count);
      }
    };
  }

  @Override
  public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {
        /* no op */
  }

  @Override
  public void onTextChanged(CharSequence sequence, int start, int before, int count) {
        /* no op */
  }

  @Override
  public void afterTextChanged(Editable editable) {
        /* no op */
  }

  interface OnTextChangedListener {

    void onTextChanged(CharSequence sequence, int start, int before, int count);
  }
}
