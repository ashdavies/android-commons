package io.ashdavies.commons.transformer;

public interface FlipTransformer<Input, Output> extends Transformer<Input, Output> {
  Input flip(Output output);
}
