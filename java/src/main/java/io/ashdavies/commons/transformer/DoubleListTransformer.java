package io.ashdavies.commons.transformer;

import java.util.ArrayList;
import java.util.List;

public class DoubleListTransformer<Input, Output> extends ListTransformer<Input, Output> implements DoubleTransformer<List<Input>, List<Output>> {

  private final DoubleTransformer<Input, Output> transformer;

  public DoubleListTransformer(DoubleTransformer<Input, Output> transformer) {
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
