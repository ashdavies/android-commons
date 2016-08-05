package io.ashdavies.commons.transformer;

import java.util.HashSet;
import java.util.Set;

public class FlipSetTransformer<Input, Output> extends SetTransformer<Input, Output>
    implements FlipTransformer<Set<Input>, Set<Output>> {
  private final FlipTransformer<Input, Output> transformer;

  public FlipSetTransformer(FlipTransformer<Input, Output> transformer) {
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
