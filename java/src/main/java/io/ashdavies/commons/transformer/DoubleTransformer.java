package io.ashdavies.commons.transformer;

public interface DoubleTransformer<Input, Output> extends Transformer<Input, Output> {

  Input flip(Output output);
}
