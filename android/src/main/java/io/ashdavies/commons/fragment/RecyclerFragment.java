package io.ashdavies.commons.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import io.ashdavies.commons.adapter.AbstractAdapter;
import io.ashdavies.commons.view.ListView;
import java.util.Collection;

public abstract class RecyclerFragment<T, VH extends AbstractAdapter.ViewHolder<T>> extends AbstractFragment implements ListView<T> {

  private final AbstractAdapter<VH, T> adapter = createAdapter();

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getRecyclerView().setAdapter(adapter);
    getRecyclerView().setHasFixedSize(true);

    getRecyclerView().addItemDecoration(getDividerItemDecoration());
    getRecyclerView().setLayoutManager(getLayoutManager());
  }

  protected abstract RecyclerView.ItemDecoration getDividerItemDecoration();

  protected abstract RecyclerView.LayoutManager getLayoutManager();

  protected abstract AbstractAdapter<VH, T> createAdapter();

  protected abstract RecyclerView getRecyclerView();

  @Override
  public void add(T t) {
    adapter.addItem(t);
  }

  @Override
  public void add(Collection<T> ts) {
    adapter.addItems(ts);
  }
}
