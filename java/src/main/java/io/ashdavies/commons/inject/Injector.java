package io.ashdavies.commons.inject;

public interface Injector<Component> extends HasComponent<Component> {

  void inject(Component component);
}
