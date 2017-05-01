package io.ashdavies.commons.transformer;

import java.util.HashSet;
import java.util.Set;

public class DoubleSetTransformer<Input, Output> extends SetTransformer<Input, Output> implements DoubleTransformer<Set<Input>, Set<Output>> {

  private final DoubleTransformer<Input, Output> transformer;

  public DoubleSetTransformer(DoubleTransformer<Input, Output> transformer) {
    super(transformer);
    this.transformer = transformer;
  }

  @Override
  public Set<Input> flip(Set<Output> outputs) {
    Set<Input> Set = new HashSet<>();

    for (Output ouput : outputs) {
      Set.add(transformer.flip(ouput));
    }

    return Set;
  }
}
