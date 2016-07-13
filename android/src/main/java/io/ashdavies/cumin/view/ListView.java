package io.ashdavies.cumin.view;

import java.util.Collection;

public interface ListView<T> extends BaseView {
    void add(T item);

    void add(Collection<T> items);

    void set(Collection<T> items);

    void clear();
}
