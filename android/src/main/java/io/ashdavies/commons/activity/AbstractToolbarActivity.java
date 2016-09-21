package io.ashdavies.commons.activity;

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

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private Toolbar getToolbar() {
    return (Toolbar) findViewById(getToolbarId());
  }

  @IdRes
  protected abstract int getToolbarId();

  protected void onActionBarSet(ActionBar actionBar) {
    // No implementation
  }
}
