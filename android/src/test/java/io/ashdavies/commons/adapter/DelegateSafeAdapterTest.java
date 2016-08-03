package io.ashdavies.commons.adapter;

import android.content.Context;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.ashdavies.commons.ApplicationTestRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.when;

@RunWith(ApplicationTestRunner.class)
public class DelegateSafeAdapterTest {
  private StubDelegateAdapter adapter;

  @Rule public MockitoRule mockito = MockitoJUnit.rule();

  @Mock AdapterDelegate<BaseAdapter.ViewHolder<String>, List<? extends String>> delegate;

  @Before
  public void setUp() {
    adapter = new StubDelegateAdapter(RuntimeEnvironment.application);
  }

  @Test
  public void assertAddItemFilterRemoved() {
    adapter.addItem(0, "Steve");
    assertEquals(0, adapter.getItemCount());
  }

  @Test
  public void assertAddItemsFilterRemoved() {
    adapter.addItems(Collections.singletonList("Francis"));
    assertEquals(0, adapter.getItemCount());
  }

  @Test
  public void assertValidFilterAccepted() {
    when(delegate.isForViewType(anyListOf(String.class), anyInt())).thenReturn(true);

    adapter.addDelegate(delegate);
    adapter.addItem(0, "Dave");

    assertEquals(1, adapter.getItemCount());
  }

  public static class StubDelegateAdapter
      extends DelegateSafeAdapter<BaseAdapter.ViewHolder<String>, String> {
    public StubDelegateAdapter(Context context) {
      super(context, new ArrayList<String>());
    }
  }
}
