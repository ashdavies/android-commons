package io.ashdavies.rx.repository;

import io.reactivex.Observable;
import java.util.Collection;

public interface Repository<Entity> {

  Observable<Collection<Entity>> get();
}
