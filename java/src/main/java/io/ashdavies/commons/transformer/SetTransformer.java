package io.ashdavies.commons.transformer;

import java.util.HashSet;
import java.util.Set;

public class SetTransformer<Input, Output> implements Transformer<Set<Input>, Set<Output>> {
  private final Transformer<Input, Output> transformer;

  public SetTransformer(Transformer<Input, Output> transformer) {
    this.transformer = transformer;
  }

  @Override
  public Set<Output> transform(Set<Input> inputs) {
    Set<Output> set = new HashSet<>();

    for (Input input : inputs) {
      set.add(transformer.transform(input));
    }

    return set;
  }
}
