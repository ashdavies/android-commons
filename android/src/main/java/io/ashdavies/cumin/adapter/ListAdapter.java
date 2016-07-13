package io.ashdavies.cumin.adapter;

import java.util.List;

public interface ListAdapter<T> {
    T getItem(int position);

    List<T> getItems();

    void addItem(int position, T item);

    void addItems(List<T> items);

    void removeItem(T item);

    void removeItem(int position);
}
