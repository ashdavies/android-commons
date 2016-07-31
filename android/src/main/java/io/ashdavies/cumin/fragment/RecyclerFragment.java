package io.ashdavies.cumin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Collection;

import io.ashdavies.cumin.adapter.BaseAdapter;
import io.ashdavies.cumin.view.ListView;

public abstract class RecyclerFragment<T, VH extends BaseAdapter.ViewHolder<T>> extends BaseFragment implements ListView<T> {
  private final BaseAdapter<VH, T> adapter = createAdapter();

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getRecyclerView().setAdapter(adapter);
    getRecyclerView().setHasFixedSize(true);

    getRecyclerView().addItemDecoration(getDividerItemDecoration());
    getRecyclerView().setLayoutManager(getLayoutManager());
  }

  protected abstract RecyclerView.ItemDecoration getDividerItemDecoration();

  protected abstract RecyclerView.LayoutManager getLayoutManager();

  protected abstract BaseAdapter<VH, T> createAdapter();

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
