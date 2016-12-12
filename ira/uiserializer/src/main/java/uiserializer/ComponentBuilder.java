package uiserializer;

import java.util.ArrayList;
import java.util.function.Consumer;

import org.i2g.ira.uibuilder.HTMLElements;

import uiserializer.components.Cmp;
import utils.collections.Collector;

public abstract class ComponentBuilder implements Cmp {

	protected final Collector<Cmp> items = new Collector<>(new ArrayList<Cmp>());

	public <T extends Cmp> void with(T t, Consumer<T> action) {
		action.accept(items.remember(t));
	}

	@Override
	public abstract void render(HTMLElements html);

}
