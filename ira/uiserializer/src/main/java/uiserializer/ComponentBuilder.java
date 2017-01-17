package uiserializer;

import static utils.collections.Collector.newCollector;

import java.util.function.Consumer;

import uiserializer.components.Component;
import utils.collections.Collector;

// FIXME Переименовать в LinearComponent.
public abstract class ComponentBuilder extends ComponentBuilderBase {

	protected final Collector<Component> items = newCollector();

	public <T extends Component> void with(T t, Consumer<T> action) {
		action.accept(items.remember(t));
	}

}
