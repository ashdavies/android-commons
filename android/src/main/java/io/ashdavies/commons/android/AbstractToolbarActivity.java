package io.ashdavies.commons.android;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public abstract class AbstractToolbarActivity extends AbstractActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setSupportActionBar(getToolbar());
    onActionBarSet(getSupportActionBar());
  }

  private Toolbar getToolbar() {
    return (Toolbar) findViewById(getToolbarId());
  }

  @IdRes
  protected abstract int getToolbarId();

  abstract void onActionBarSet(ActionBar actionBar);

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      super.finish();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
