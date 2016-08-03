package io.ashdavies.commons.transformer;

public interface Transformer<Input, Output> {
  Output transform(Input input);
}
