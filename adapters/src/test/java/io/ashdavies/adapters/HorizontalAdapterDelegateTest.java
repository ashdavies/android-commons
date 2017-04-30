package io.ashdavies.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;

@RunWith(MockitoJUnitRunner.class)
public class HorizontalAdapterDelegateTest {

  private static final int PARENT_WIDTH = 128;
  private static final float CHILD_WIDTH_SCALE = 0.5f;

  private StubHorizontalAdapterDelegate delegate;

  @Mock LayoutInflater inflater;
  @Mock ViewGroup parent;
  @Mock View view;

  @Captor ArgumentCaptor<ViewGroup.LayoutParams> captor;

  @Before
  public void setUp() {
    delegate = new StubHorizontalAdapterDelegate(inflater);
    given(inflater.inflate(anyInt(), eq(parent), eq(false))).willReturn(view);
  }

  @Test
  public void shouldOnCreateViewScaled() {
    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(0, 0);
    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
    params.height = ViewGroup.LayoutParams.MATCH_PARENT;

    given(view.getLayoutParams()).willReturn(params);
    given(parent.getWidth()).willReturn(PARENT_WIDTH);

    delegate.onCreateView(parent);

    then(view).should().getLayoutParams();
    then(view).should().setLayoutParams(captor.capture());

    assertThat(captor.getValue().width).isEqualTo((int) (PARENT_WIDTH * CHILD_WIDTH_SCALE));
  }

  @Test
  public void shouldOnCreateViewNormal() {
    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(0, 0);
    params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
    given(view.getLayoutParams()).willReturn(params);

    delegate.onCreateView(parent);

    then(view).should().getLayoutParams();
    then(view).should(never()).setLayoutParams(any(ViewGroup.LayoutParams.class));
  }

  @Test
  public void shouldGetDefaultWidth() {
    given(parent.getWidth()).willReturn(PARENT_WIDTH);

    assertThat(delegate.getWidth(parent)).isEqualTo(PARENT_WIDTH);
  }

  public static class StubHorizontalAdapterDelegate extends HorizontalAdapterDelegate<RecyclerView.ViewHolder, String> {

    StubHorizontalAdapterDelegate(LayoutInflater inflater) {
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
