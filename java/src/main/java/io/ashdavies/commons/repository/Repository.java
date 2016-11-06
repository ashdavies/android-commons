package io.ashdavies.commons.repository;

import java.util.Collection;

public interface Repository<T> {

  Collection<T> get();
}
