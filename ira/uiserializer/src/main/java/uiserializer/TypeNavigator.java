package uiserializer;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

import java.util.Collection;

public class TypeNavigator {

	private final Class<?> cursor;

	public TypeNavigator(Class<?> cursor) {
		this.cursor = cursor;
	}

	public Collection<MethodModel> models() {
		return of(cursor.getMethods()).map(MethodModel::new).collect(toList());
	}

	public String simpleName() {
		return cursor.getSimpleName();
	}

}
