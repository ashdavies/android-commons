package io.ashdavies.commons.adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class DelegateSafeAdapter<VH extends BaseAdapter.ViewHolder<T>, T>
    extends DelegateAdapter<VH, T> {
  public DelegateSafeAdapter(Context context, List<T> items) {
    super(context, items);
  }

  @Override
  public void addItem(int position, T item) {
    if (hasDelegate(Collections.singletonList(item), 0)) {
      super.addItem(position, item);
    }
  }

  @Override
  public void addItems(Collection<T> collection) {
    super.addItems(filter(collection));
  }

  private List<T> filter(Collection<T> items) {
    List<T> list = new ArrayList<>(items);
    List<T> filtered = new ArrayList<>();

    for (int i = 0; i < list.size(); i++) {
      if (hasDelegate(list, i)) {
        filtered.add(list.get(i));
      }
    }

    return filtered;
  }
}
