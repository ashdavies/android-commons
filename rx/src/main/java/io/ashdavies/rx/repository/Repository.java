package io.ashdavies.rx.repository;

import io.reactivex.Flowable;
import java.util.Collection;

public interface Repository<T> {

  Flowable<Collection<T>> get();
}
