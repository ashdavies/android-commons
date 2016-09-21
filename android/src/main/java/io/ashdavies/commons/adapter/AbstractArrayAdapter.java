package io.ashdavies.commons.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class AbstractArrayAdapter<T> extends ArrayAdapter<T> {

  private final LayoutInflater inflater;
  private final List<T> items;

  public AbstractArrayAdapter(Context context, int resource) {
    super(context, resource);

    this.inflater = LayoutInflater.from(context);
    this.items = new ArrayList<>();
  }

  public AbstractArrayAdapter(Context context, int resource, int textViewResourceId) {
    super(context, resource, textViewResourceId);

    this.inflater = LayoutInflater.from(context);
    this.items = new ArrayList<>();
  }

  public AbstractArrayAdapter(Context context, int resource, T[] objects) {
    super(context, resource, objects);

    this.inflater = LayoutInflater.from(context);
    this.items = Arrays.asList(objects);
  }

  public AbstractArrayAdapter(Context context, int resource, int textViewResourceId, T[] objects) {
    super(context, resource, textViewResourceId, objects);

    this.inflater = LayoutInflater.from(context);
    this.items = Arrays.asList(objects);
  }

  public AbstractArrayAdapter(Context context, int resource, List<T> objects) {
    super(context, resource, objects);

    this.inflater = LayoutInflater.from(context);
    this.items = objects;
  }

  public AbstractArrayAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
    super(context, resource, textViewResourceId, objects);

    this.inflater = LayoutInflater.from(context);
    this.items = objects;
  }

  @Override
  public int getCount() {
    return items.size();
  }

  @Override
  public T getItem(int position) {
    return items.get(position);
  }

  public void addItem(T item) {
    addItem(0, item);
  }

  public void addItem(int position, T item) {
    items.add(position, item);
    notifyDataSetChanged();
  }

  public void addItems(Collection<T> collection) {
    items.addAll(collection);
    notifyDataSetChanged();
  }

  public void removeItem(T item) {
    removeItem(items.indexOf(item));
    notifyDataSetChanged();
  }

  public void removeItem(int position) {
    items.remove(position);
    notifyDataSetChanged();
  }

  public void clearItems() {
    items.clear();
    notifyDataSetChanged();
  }

  public void setItems(List<T> list) {
    items.clear();
    items.addAll(list);
    notifyDataSetChanged();
  }

  public LayoutInflater getInflater() {
    return inflater;
  }
}
