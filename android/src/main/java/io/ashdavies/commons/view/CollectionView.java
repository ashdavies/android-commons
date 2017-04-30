package io.ashdavies.commons.view;

import java.util.Collection;

public interface CollectionView<T> extends AbstractView {

  void add(T item);

  void add(Collection<T> items);
}
