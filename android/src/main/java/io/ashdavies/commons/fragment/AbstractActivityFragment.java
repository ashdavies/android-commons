package io.ashdavies.commons.fragment;

import android.content.Context;
import io.ashdavies.commons.activity.AbstractActivity;

public abstract class AbstractActivityFragment<Activity extends AbstractActivity> extends AbstractFragment {

  private Activity activity;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

    //noinspection unchecked
    activity = (Activity) getActivity();
  }

  protected Activity getParentActivity() {
    return activity;
  }
}
