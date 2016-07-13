package io.ashdavies.cumin.storage;

import java.util.Collection;

public interface Storage<Id, T> {
    T get(Id id) throws IndexNotFoundException;

    Collection<T> getAll();

    void set(Id id, T t);

    public class IndexNotFoundException extends RuntimeException {
    }
}
