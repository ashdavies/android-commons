package io.ashdavies.commons.adapter;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;

@RunWith(MockitoJUnitRunner.class)
public class DelegateSafeAdapterTest {

  private static final String STEVE = "Steve";
  private static final String FRANCIS = "Francis";
  private static final String DAVE = "Dave";

  private StubDelegateAdapter adapter;

  @Mock AdapterDelegate<AbstractAdapter.ViewHolder<String>, List<? extends String>> delegate;
  @Mock Context context;

  @Before
  public void setUp() {
    adapter = new StubDelegateAdapter(context);
  }

  @Test
  public void shouldAddItemFilterRemoved() {
    adapter.addItem(0, STEVE);
    assertThat(adapter.getItemCount()).isEqualTo(0);
  }

  @Test
  public void shouldNotAddFilteredItem() {
    adapter.addItems(Collections.singletonList(FRANCIS));
    assertThat(adapter.getItemCount()).isEqualTo(0);
  }

  @Test
  public void shouldValidFilterAccepted() {
    given(delegate.isForViewType(anyListOf(String.class), anyInt())).willReturn(true);

    adapter.addDelegate(delegate);
    adapter.addItem(0, DAVE);

    assertThat(adapter.getItemCount()).isEqualTo(1);
  }

  public static class StubDelegateAdapter extends DelegateSafeAdapter<AbstractAdapter.ViewHolder<String>, String> {

    StubDelegateAdapter(Context context) {
      super(context, new ArrayList<String>());
    }
  }
}
