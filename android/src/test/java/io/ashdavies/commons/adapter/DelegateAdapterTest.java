package io.ashdavies.commons.adapter;

import android.content.Context;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class DelegateAdapterTest {

  private static final String BOB = "Bob";
  private static final String CHRIS = "Chris";

  private StubDelegateAdapter adapter;

  @Mock AdapterDelegate<AbstractAdapter.ViewHolder<String>, List<? extends String>> delegate;
  @Mock Context context;

  @Before
  public void setUp() {
    adapter = new StubDelegateAdapter(context, Collections.singletonList(BOB));
  }

  @Test
  public void shouldDoesNotHaveDelegate() throws Exception {
    assertEquals(false, adapter.hasDelegate(Collections.singletonList(CHRIS), 0));
  }

  @Test
  public void shouldHaveDelegate() throws Exception {
    given(delegate.isForViewType(anyListOf(String.class), eq(0))).willReturn(true);

    adapter.addDelegate(delegate);
    adapter.hasDelegate(anyListOf(String.class), eq(0));

    then(delegate).should().isForViewType(anyListOf(String.class), eq(0));
  }

  @Test
  public void shouldOnBindViewHolder() throws Exception {
    given(delegate.isForViewType(anyListOf(String.class), eq(0))).willReturn(true);

    //noinspection unchecked
    AbstractAdapter.ViewHolder<String> holder = mock(AbstractAdapter.ViewHolder.class);

    adapter.addDelegate(delegate);
    adapter.onBindViewHolder(holder, 0);

    then(delegate).should().isForViewType(anyListOf(String.class), eq(0));
    then(delegate).should().onBindViewHolder(anyListOf(String.class), eq(0), eq(holder));
  }

  @Test
  public void shouldGetItemViewType() throws Exception {
    given(delegate.isForViewType(anyListOf(String.class), eq(0))).willReturn(true);
    given(delegate.getItemViewType()).willReturn(1);

    adapter.addDelegate(delegate);
    adapter.getItemViewType(0);

    then(delegate).should().isForViewType(anyListOf(String.class), eq(0));
  }

  @Test
  public void shouldOnCreateViewHolder() throws Exception {
    adapter.addDelegate(delegate);
    adapter.onCreateViewHolder(null, 0);

    then(delegate).should().onCreateViewHolder(null);
  }

  public static class StubDelegateAdapter extends DelegateAdapter<AbstractAdapter.ViewHolder<String>, String> {

    StubDelegateAdapter(Context context, List<String> items) {
      super(context, items);
    }
  }
}
