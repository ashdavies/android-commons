package io.ashdavies.cumin.repository;

import java.util.Collection;

import rx.Observable;

public interface Repository<Entity> {
    Observable<Collection<Entity>> get();
}
