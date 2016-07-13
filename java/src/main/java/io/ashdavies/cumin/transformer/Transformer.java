package io.ashdavies.cumin.transformer;

public interface Transformer<Input, Output> {
    Output transform(Input input);
}
