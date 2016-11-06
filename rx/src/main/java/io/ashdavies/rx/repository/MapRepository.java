package io.ashdavies.rx.repository;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import java.util.HashMap;
import java.util.Map;

public class MapRepository<T, Id> implements Repository<T, Id> {

  private final Map<Id, T> repository;

  public MapRepository() {
    this(new HashMap<Id, T>());
  }

  private MapRepository(Map<Id, T> map) {
    this.repository = map;
  }

  @Override
  public Flowable<T> get(Id id) throws IndexNotFoundException {
    if (!repository.containsKey(id)) {
      throw new IndexNotFoundException();
    }

    return Flowable.just(repository.get(id));
  }

  @Override
  public Flowable<T> getAll() {
    return Flowable.fromIterable(repository.values());
  }

  @Override
  public Completable put(final T t, final Resolver<T, Id> resolver) {
    return Completable.create(new CompletableOnSubscribe() {
      @Override
      public void subscribe(CompletableEmitter emitter) throws Exception {
        repository.put(resolver.resolve(t), t);
        emitter.onComplete();
      }
    });
  }
}
