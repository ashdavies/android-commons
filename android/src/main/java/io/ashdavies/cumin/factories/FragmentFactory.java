package io.ashdavies.cumin.factories;

import android.content.Context;
import android.support.v4.app.Fragment;

public interface FragmentFactory extends InstanceFactory<Fragment> {
  String getTitle(Context context);
}
