package io.ashdavies.commons.transformer;

import java.util.ArrayList;
import java.util.List;

public class ListTransformer<Input, Output> implements Transformer<List<Input>, List<Output>> {
  private final Transformer<Input, Output> transformer;

  public ListTransformer(Transformer<Input, Output> transformer) {
    this.transformer = transformer;
  }

  @Override
  public List<Output> transform(List<Input> inputs) {
    List<Output> list = new ArrayList<>();

    for (Input input : inputs) {
      list.add(transformer.transform(input));
    }

    return list;
  }
}
