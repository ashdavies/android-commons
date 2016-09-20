package io.ashdavies.commons.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseAdapter<VH extends BaseAdapter.ViewHolder<T>, T> extends RecyclerView.Adapter<VH> implements ListAdapter<T> {

  private final LayoutInflater inflater;
  private final List<T> items;

  public BaseAdapter(Context context) {
    this(context, new ArrayList<T>());
  }

  public BaseAdapter(Context context, List<T> items) {
    this(LayoutInflater.from(context), items);
  }

  public BaseAdapter(LayoutInflater inflater, List<T> items) {
    this.inflater = inflater;
    this.items = items;
  }

  @Override
  public void onBindViewHolder(VH holder, int position) {
    holder.bind(items.get(position));
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  @Override
  public T getItem(int position) {
    return items.get(position);
  }

  @Override
  public List<T> getItems() {
    return items;
  }

  @Override
  public void addItem(T item) {
    addItem(0, item);
  }

  @Override
  public void addItem(int position, T item) {
    items.add(position, item);
    notifyItemInserted(position);
  }

  @Override
  public void addItems(Collection<T> collection) {
    items.addAll(collection);
    notifyDataSetChanged();
  }

  @Override
  public void removeItem(T item) {
    removeItem(items.indexOf(item));
  }

  @Override
  public void removeItem(int position) {
    items.remove(position);
    notifyItemRemoved(position);
  }

  protected LayoutInflater getInflater() {
    return inflater;
  }

  public static abstract class ViewHolder<T> extends RecyclerView.ViewHolder {

    private WeakReference<Context> context;

    public ViewHolder(Context context, View view) {
      super(view);
      bind(context);
    }

    public Context getContext() {
      return context.get();
    }

    private void bind(Context context) {
      this.context = new WeakReference<>(context);
    }

    protected abstract void bind(T t);
  }
}
