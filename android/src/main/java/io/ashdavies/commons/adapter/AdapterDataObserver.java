package io.ashdavies.commons.adapter;

import android.support.v7.widget.RecyclerView;
import rx.Observable;
import rx.subjects.PublishSubject;

public final class AdapterDataObserver extends RecyclerView.AdapterDataObserver {

  private final PublishSubject<AdapterEvent> subject;

  private AdapterDataObserver() {
    this.subject = PublishSubject.create();
  }

  public static Observable<AdapterEvent> observe(RecyclerView.Adapter adapter) {
    AdapterDataObserver observer = new AdapterDataObserver();
    adapter.registerAdapterDataObserver(observer);
    return observer.getStream();
  }

  private PublishSubject<AdapterEvent> getStream() {
    return subject;
  }

  @Override
  public void onChanged() {
    subject.onNext(new AdapterEvent(AdapterEvent.Type.CHANGED));
  }

  @Override
  public void onItemRangeChanged(int positionStart, int itemCount) {
    subject.onNext(
        new AdapterEvent(
            AdapterEvent.Type.RANGE_CHANGED, new AdapterEvent.Range(positionStart, itemCount)));
  }

  @Override
  public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
    subject.onNext(
        new AdapterEvent(
            AdapterEvent.Type.RANGE_CHANGED,
            new AdapterEvent.Range(positionStart, itemCount),
            payload));
  }

  @Override
  public void onItemRangeInserted(int positionStart, int itemCount) {
    subject.onNext(
        new AdapterEvent(
            AdapterEvent.Type.RANGE_INSERTED, new AdapterEvent.Range(positionStart, itemCount)));
  }

  @Override
  public void onItemRangeRemoved(int positionStart, int itemCount) {
    subject.onNext(
        new AdapterEvent(
            AdapterEvent.Type.RANGE_REMOVED, new AdapterEvent.Range(positionStart, itemCount)));
  }

  @Override
  public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
    subject.onNext(
        new AdapterEvent(
            AdapterEvent.Type.RANGE_MOVED,
            new AdapterEvent.Range(fromPosition, toPosition, itemCount)));
  }

  public static class AdapterEvent {

    private final Type type;
    private final Range range;
    private final Object payload;

    public AdapterEvent(Type type) {
      this(type, null);
    }

    public AdapterEvent(Type type, Range range) {
      this(type, range, null);
    }

    public AdapterEvent(Type type, Range range, Object payload) {
      this.type = type;
      this.range = range;
      this.payload = payload;
    }

    public Type getType() {
      return type;
    }

    public Range getRange() {
      return range;
    }

    public Object getPayload() {
      return payload;
    }

    public enum Type {
      CHANGED,
      RANGE_CHANGED,
      RANGE_INSERTED,
      RANGE_REMOVED,
      RANGE_MOVED
    }

    public static class Range {

      private final int from;
      private final int to;
      private final int count;

      public Range(int from, int count) {
        this(from, from + count, count);
      }

      public Range(int from, int to, int count) {
        this.from = from;
        this.to = to;
        this.count = count;
      }

      public int getFrom() {
        return from;
      }

      public int getTo() {
        return to;
      }

      public int getCount() {
        return count;
      }
    }
  }
}
