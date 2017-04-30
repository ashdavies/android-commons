package io.ashdavies.adapters;

import android.support.v7.widget.RecyclerView;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class AdapterDelegateManagerTest {

  private static final String BOB = "Bob";
  private static final String FRED = "Fred";
  private static final String CHRIS = "Chris";
  private static final String DAVE = "Dave";
  private static final String FRANK = "Frank";
  private static final String STEVE = "Steve";

  private AdapterDelegateManager<RecyclerView.ViewHolder, List<String>> manager;

  @Mock AdapterDelegate<RecyclerView.ViewHolder, List<String>> delegate;

  @Before
  public void setUp() {
    manager = new AdapterDelegateManager<>();
    manager.addDelegate(delegate);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowDuplicateAdapterDelegateException() {
    manager.addDelegate(delegate);
  }

  @Test
  public void shouldReplaceAdapterDelegate() {
    manager.addDelegate(delegate, true);
  }

  @Test
  public void shouldHaveNoDelegateForItems() {
    assertThat(manager.hasDelegate(Collections.singletonList(BOB), 0)).isFalse();
  }

  @Test
  public void shouldHaveDelegateForItems() {
    List<String> list = Collections.singletonList(FRED);
    given(delegate.isForViewType(list, 0)).willReturn(true);

    manager.hasDelegate(list, 0);

    then(delegate).should().isForViewType(list, 0);
  }

  @Test
  public void shouldRemoveDelegateByReference() {
    manager.removeDelegate(delegate);
    manager.addDelegate(delegate);
  }

  @Test
  public void shouldRemoveDelegateByViewType() {
    manager.removeDelegate(0);
    manager.addDelegate(delegate);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldHaveNoAdapterDelegateForPosition() {
    manager.getItemViewType(Collections.singletonList(CHRIS), 0);
  }

  @Test
  public void shouldHaveAdapterDelegateForPosition() {
    List<String> list = Collections.singletonList(DAVE);
    given(delegate.isForViewType(list, 0)).willReturn(true);

    manager.getItemViewType(list, 0);

    then(delegate).should().isForViewType(list, 0);
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowOnCreateViewHolderForUnknownViewTypeException() {
    manager.onCreateViewHolder(null, 1);
  }

  @Test
  public void shouldCreateViewHolder() {
    RecyclerView.ViewHolder holder = mock(RecyclerView.ViewHolder.class);
    given(delegate.onCreateViewHolder(null)).willReturn(holder);

    manager.onCreateViewHolder(null, 0);

    then(delegate).should().onCreateViewHolder(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowOnBindViewHolderForUnknownViewTypeException() {
    manager.onBindViewHolder(Collections.singletonList(FRANK), 0, mock(RecyclerView.ViewHolder.class));
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowOnBindViewHolderForInvalidViewTypeException() {
    List<String> list = Collections.singletonList(FRANK);
    given(delegate.isForViewType(list, 0)).willReturn(true);
    given(delegate.getItemViewType()).willReturn(1);

    manager.onBindViewHolder(list, 0, mock(RecyclerView.ViewHolder.class));
  }

  @Test
  public void shouldBindViewHolder() {
    List<String> list = Collections.singletonList(STEVE);
    given(delegate.isForViewType(list, 0)).willReturn(true);

    RecyclerView.ViewHolder holder = mock(RecyclerView.ViewHolder.class);
    manager.onBindViewHolder(list, 0, holder);

    then(delegate).should().isForViewType(list, 0);
    then(delegate).should().onBindViewHolder(list, 0, holder);
  }
}
