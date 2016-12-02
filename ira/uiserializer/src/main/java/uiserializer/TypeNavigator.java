package uiserializer;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import utils.Safer;

public class TypeNavigator {

	private final Class<?> cursor;

	public TypeNavigator(Class<?> cursor) {
		this.cursor = cursor;

	}

	public Collection<Method> methods() {
		final List<Method> methods = Safer.safe(() -> new ArrayList<Method>());
		for (final Method method : cursor.getMethods()) {
			methods.add(method);
		}
		return methods;
	}

}
