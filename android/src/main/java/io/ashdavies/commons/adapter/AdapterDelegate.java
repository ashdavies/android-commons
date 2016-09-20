package io.ashdavies.commons.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

public interface AdapterDelegate<VH, T> {

  int getItemViewType();

  boolean isForViewType(@NonNull T items, int position);

  @NonNull
  VH onCreateViewHolder(ViewGroup parent);

  void onBindViewHolder(@NonNull T items, int position, @NonNull VH holder);
}
