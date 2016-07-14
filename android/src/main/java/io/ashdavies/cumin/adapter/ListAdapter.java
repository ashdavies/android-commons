package io.ashdavies.cumin.adapter;

import java.util.Collection;
import java.util.List;

public interface ListAdapter<T> {
    T getItem(int position);

    List<T> getItems();

    void addItem(T item);

    void addItem(int position, T item);

    void addItems(Collection<T> items);

    void removeItem(T item);

    void removeItem(int position);
}
