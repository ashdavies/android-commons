package io.ashdavies.commons.component;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class SimpleTextWatcher implements TextWatcher {

  @Override
  public void beforeTextChanged(CharSequence sequence, int i, int i1, int i2) {
        /* no op */
  }

  @Override
  public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {
        /* no op */
  }

  @Override
  public void afterTextChanged(Editable editable) {
        /* no op */
  }
}
