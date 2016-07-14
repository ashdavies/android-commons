package io.ashdavies.cumin.adapter;

import android.content.Context;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RuntimeEnvironment;

import java.util.Collections;
import java.util.List;

import io.ashdavies.cumin.ApplicationTestRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(ApplicationTestRunner.class)
public class DelegateAdapterTest {
    private StubDelegateAdapter adapter;

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Mock
    AdapterDelegate<BaseAdapter.ViewHolder<String>, List<? extends String>> delegate;

    @Before
    public void setUp() {
        adapter = new StubDelegateAdapter(RuntimeEnvironment.application, Collections.singletonList("Bob"));
    }

    @Test
    public void assertDoesNotHaveDelegate() {
        assertEquals(false, adapter.hasDelegate(Collections.singletonList("Chris"), 0));
    }

    @Test
    public void assertHasDelegate() {
        when(delegate.isForViewType(anyListOf(String.class), eq(0))).thenReturn(true);

        adapter.addDelegate(delegate);
        assertEquals(true, adapter.hasDelegate(anyListOf(String.class), eq(0)));
        verify(delegate, times(1)).isForViewType(anyListOf(String.class), eq(0));
    }

    @Test
    public void assertOnBindViewHolder() {
        when(delegate.isForViewType(anyListOf(String.class), eq(0))).thenReturn(true);

        //noinspection unchecked
        BaseAdapter.ViewHolder<String> holder = mock(BaseAdapter.ViewHolder.class);

        adapter.addDelegate(delegate);
        adapter.onBindViewHolder(holder, 0);
        verify(delegate, times(1)).isForViewType(anyListOf(String.class), eq(0));
        verify(delegate, times(1)).onBindViewHolder(anyListOf(String.class), eq(0), eq(holder));
    }

    @Test
    public void assertGetItemViewType() {
        when(delegate.isForViewType(anyListOf(String.class), eq(0))).thenReturn(true);
        when(delegate.getItemViewType()).thenReturn(1);

        adapter.addDelegate(delegate);
        assertEquals(1, adapter.getItemViewType(0));
        verify(delegate, times(1)).isForViewType(anyListOf(String.class), eq(0));
    }

    @Test
    public void assertOnCreateViewHolder() {
        adapter.addDelegate(delegate);
        adapter.onCreateViewHolder(null, 0);
        verify(delegate).onCreateViewHolder(null);
    }

    public static class StubDelegateAdapter extends DelegateAdapter<BaseAdapter.ViewHolder<String>, String> {
        public StubDelegateAdapter(Context context, List<String> items) {
            super(context, items);
        }
    }
}
