package io.ashdavies.cumin.repository;

import java.util.Collection;

public interface Repository<Entity> {
    Collection<Entity> get();
}
