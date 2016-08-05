package io.ashdavies.commons.transformer;

import java.util.ArrayList;
import java.util.List;

public class FlipListTransformer<Input, Output> extends ListTransformer<Input, Output>
    implements FlipTransformer<List<Input>, List<Output>> {
  private final FlipTransformer<Input, Output> transformer;

  public FlipListTransformer(FlipTransformer<Input, Output> transformer) {
    super(transformer);
    this.transformer = transformer;
  }

  @Override
  public List<Input> flip(List<Output> outputs) {
    List<Input> list = new ArrayList<>();

    for (Output ouput : outputs) {
      list.add(transformer.flip(ouput));
    }

    return list;
  }
}
