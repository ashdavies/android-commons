package io.ashdavies.cumin.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.ashdavies.cumin.ApplicationTestRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(ApplicationTestRunner.class)
public class HorizontalAdapterDelegateTest {
    private static final int PARENT_WIDTH = 128;
    private static final float CHILD_WIDTH_SCALE = 0.5f;

    private StubHorizontalAdapterDelegate delegate;

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Mock
    LayoutInflater inflater;

    @Mock
    ViewGroup parent;

    @Mock
    View view;

    @Before
    public void setUp() {
        delegate = new StubHorizontalAdapterDelegate(inflater);
        when(inflater.inflate(anyInt(), eq(parent), eq(false))).thenReturn(view);
    }

    @Test
    public void assertLayoutParams() {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(0, 0);
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;

        assertEquals(ViewGroup.LayoutParams.MATCH_PARENT, params.width);
        assertEquals(ViewGroup.LayoutParams.MATCH_PARENT, params.height);
    }

    @Test
    public void assertOnCreateViewScaled() {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(0, 0);
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;

        when(view.getLayoutParams()).thenReturn(params);
        when(parent.getWidth()).thenReturn(PARENT_WIDTH);
        delegate.onCreateView(parent);

        ArgumentCaptor<ViewGroup.LayoutParams> captor = ArgumentCaptor.forClass(ViewGroup.LayoutParams.class);
        verify(view, times(1)).getLayoutParams();
        verify(view, times(1)).setLayoutParams(captor.capture());

        assertEquals((int) (PARENT_WIDTH * CHILD_WIDTH_SCALE), captor.getValue().width);
    }

    @Test
    public void assertOnCreateViewNormal() {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(0, 0);
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        when(view.getLayoutParams()).thenReturn(params);
        delegate.onCreateView(parent);

        verify(view, times(1)).getLayoutParams();
        verify(view, times(0)).setLayoutParams(any(ViewGroup.LayoutParams.class));
    }

    @Test
    public void assertGetDefaultWidth() {
        ViewGroup parent = mock(ViewGroup.class);
        when(parent.getWidth()).thenReturn(PARENT_WIDTH);
        assertEquals(PARENT_WIDTH, delegate.getWidth(parent));
    }

    public static class StubHorizontalAdapterDelegate extends HorizontalAdapterDelegate<RecyclerView.ViewHolder, String> {
        public StubHorizontalAdapterDelegate(LayoutInflater inflater) {
            super(inflater);
        }

        @Override
        protected int getLayoutId() {
            return 0;
        }

        @Override
        protected float getWidthScale() {
            return CHILD_WIDTH_SCALE;
        }

        @Override
        public int getItemViewType() {
            return 0;
        }

        @Override
        public boolean isForViewType(@NonNull String items, int position) {
            return false;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull String items, int position, @NonNull RecyclerView.ViewHolder holder) {

        }
    }
}
