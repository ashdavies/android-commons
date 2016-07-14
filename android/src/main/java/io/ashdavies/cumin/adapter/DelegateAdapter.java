package io.ashdavies.cumin.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

public abstract class DelegateAdapter<VH extends BaseAdapter.ViewHolder<T>, T>
    extends BaseAdapter<VH, T> {
  private final AdapterDelegateManager<VH, List<? extends T>> delegates =
      new AdapterDelegateManager<>();

  public DelegateAdapter(Context context, List<T> items) {
    super(context, items);
  }

  protected void addDelegate(AdapterDelegate<VH, List<? extends T>> delegate) {
    delegates.addDelegate(delegate);
  }

  protected boolean hasDelegate(List<? extends T> items, int position) {
    return delegates.hasDelegate(items, position);
  }

  @Override
  public void onBindViewHolder(VH holder, int position) {
    delegates.onBindViewHolder(getItems(), position, holder);
  }

  @Override
  public int getItemViewType(int position) {
    return delegates.getItemViewType(getItems(), position);
  }

  @Override
  public VH onCreateViewHolder(ViewGroup parent, int viewType) {
    return delegates.onCreateViewHolder(parent, viewType);
  }
}
