package io.ashdavies.rx.repository;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;

public class ListRepository<T> implements Repository<T, Integer> {

  private final List<T> list;

  public ListRepository() {
    this(new ArrayList<T>());
  }

  private ListRepository(List<T> list) {
    this.list = list;
  }

  @Override
  public Single<T> get(Integer index) throws IndexOutOfBoundsException {
    return Single.just(list.get(index));
  }

  @Override
  public Flowable<T> getAll() {
    return Flowable.fromIterable(list);
  }

  @Override
  public Completable put(final T t, final Resolver<T, Integer> resolver) {
    return Completable.create(new CompletableOnSubscribe() {
      @Override
      public void subscribe(CompletableEmitter emitter) throws Exception {
        list.set(resolver.resolve(t), t);
        emitter.onComplete();
      }
    });
  }
}
