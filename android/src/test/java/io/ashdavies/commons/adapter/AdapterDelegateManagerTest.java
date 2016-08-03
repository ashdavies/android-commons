package io.ashdavies.commons.adapter;

import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;
import java.util.List;

import io.ashdavies.commons.ApplicationTestRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(ApplicationTestRunner.class)
public class AdapterDelegateManagerTest {
  private AdapterDelegateManager<RecyclerView.ViewHolder, List<String>> manager;

  @Rule public MockitoRule mockito = MockitoJUnit.rule();

  @Mock AdapterDelegate<RecyclerView.ViewHolder, List<String>> delegate;

  @Before
  public void setUp() {
    manager = new AdapterDelegateManager<>();
    manager.addDelegate(delegate);
  }

  @Test(expected = IllegalArgumentException.class)
  public void assertDuplicateAdapterDelegateException() {
    manager.addDelegate(delegate);
  }

  @Test
  public void assertReplacedAdapterDelegate() {
    manager.addDelegate(delegate, true);
  }

  @Test
  public void assertHasNoDelegateForItems() {
    List<String> list = Collections.singletonList("Bob");
    assertEquals(false, manager.hasDelegate(list, 0));
  }

  @Test
  public void assertHasDelegateForItems() {
    List<String> list = Collections.singletonList("Fred");
    when(delegate.isForViewType(list, 0)).thenReturn(true);

    assertEquals(true, manager.hasDelegate(list, 0));
    verify(delegate, times(1)).isForViewType(list, 0);
  }

  @Test
  public void assertRemovedDelegateByReference() {
    manager.removeDelegate(delegate);
    manager.addDelegate(delegate);
  }

  @Test
  public void assertRemovedDelegateByViewType() {
    manager.removeDelegate(0);
    manager.addDelegate(delegate);
  }

  @Test(expected = IllegalArgumentException.class)
  public void assertNoAdapterDelegateForPosition() {
    List<String> list = Collections.singletonList("Chris");
    manager.getItemViewType(list, 0);
  }

  @Test
  public void assertHasAdapterDelegateForPosition() {
    List<String> list = Collections.singletonList("Dave");
    when(delegate.isForViewType(list, 0)).thenReturn(true);

    assertEquals(0, manager.getItemViewType(list, 0));
    verify(delegate, times(1)).isForViewType(list, 0);
  }

  @Test(expected = NullPointerException.class)
  public void assertOnCreateViewHolderForUnknownViewTypeException() {
    manager.onCreateViewHolder(null, 1);
  }

  @Test
  public void assertOnCreateViewHolder() {
    RecyclerView.ViewHolder holder = mock(RecyclerView.ViewHolder.class);
    when(delegate.onCreateViewHolder(null)).thenReturn(holder);

    assertEquals(holder, manager.onCreateViewHolder(null, 0));
    verify(delegate, times(1)).onCreateViewHolder(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void assertOnBindViewHolderForUnknownViewTypeException() {
    manager.onBindViewHolder(
        Collections.singletonList("Frank"), 0, mock(RecyclerView.ViewHolder.class));
  }

  @Test(expected = NullPointerException.class)
  public void assertOnBindViewHolderForInvalidViewTypeException() {
    List<String> list = Collections.singletonList("Frank");
    when(delegate.isForViewType(list, 0)).thenReturn(true);
    when(delegate.getItemViewType()).thenReturn(1);
    manager.onBindViewHolder(list, 0, mock(RecyclerView.ViewHolder.class));
  }

  @Test
  public void assertOnBindViewHolder() {
    List<String> list = Collections.singletonList("Steve");
    when(delegate.isForViewType(list, 0)).thenReturn(true);

    RecyclerView.ViewHolder holder = mock(RecyclerView.ViewHolder.class);
    manager.onBindViewHolder(list, 0, holder);

    verify(delegate, times(1)).isForViewType(list, 0);
    verify(delegate, times(1)).onBindViewHolder(list, 0, holder);
  }
}
