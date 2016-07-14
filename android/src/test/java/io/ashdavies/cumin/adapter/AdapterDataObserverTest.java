package io.ashdavies.cumin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.ashdavies.cumin.ApplicationTestRunner;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;

@RunWith(ApplicationTestRunner.class)
public class AdapterDataObserverTest {
    private AdapterStub adapter;

    private Observable<AdapterDataObserver.AdapterEvent> observable;
    private TestSubscriber<AdapterDataObserver.AdapterEvent> subscriber;

    @Before
    public void setUp() {
        adapter = new AdapterStub();

        observable = AdapterDataObserver.observe(adapter);
        subscriber = new TestSubscriber<>();

        observable.subscribe(subscriber);
    }

    @Test
    public void assertOnChanged() {
        adapter.notifyDataSetChanged();

        List<AdapterDataObserver.AdapterEvent> events = subscriber.getOnNextEvents();

        assertEquals(1, events.size());
        assertEquals(AdapterDataObserver.AdapterEvent.Type.CHANGED, events.get(0).getType());
    }

    @Test
    public void assertOnItemRangeChanged() {
        adapter.notifyItemRangeChanged(0, 0);

        List<AdapterDataObserver.AdapterEvent> events = subscriber.getOnNextEvents();

        assertEquals(1, events.size());
        assertEquals(AdapterDataObserver.AdapterEvent.Type.RANGE_CHANGED, events.get(0).getType());
    }

    @Test
    public void assertOnItemInserted() {
        adapter.notifyItemInserted(0);

        List<AdapterDataObserver.AdapterEvent> events = subscriber.getOnNextEvents();

        assertEquals(1, events.size());
        assertEquals(AdapterDataObserver.AdapterEvent.Type.RANGE_INSERTED, events.get(0).getType());
    }

    @Test
    public void assertOnItemRemoved() {
        adapter.notifyItemRemoved(0);

        List<AdapterDataObserver.AdapterEvent> events = subscriber.getOnNextEvents();

        assertEquals(1, events.size());
        assertEquals(AdapterDataObserver.AdapterEvent.Type.RANGE_REMOVED, events.get(0).getType());
    }

    @Test
    public void assertOnItemMoved() {
        adapter.notifyItemMoved(0, 0);

        List<AdapterDataObserver.AdapterEvent> events = subscriber.getOnNextEvents();

        assertEquals(1, events.size());
        assertEquals(AdapterDataObserver.AdapterEvent.Type.RANGE_MOVED, events.get(0).getType());
    }

    public static class AdapterStub extends RecyclerView.Adapter<AdapterStub.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View view) {
                super(view);
            }
        }
    }
}
