package uiserializer;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Supplier;

public interface FromClassBuilder {

	<T> T buildFrom(Class<T> executedMethodOwnerClass, Map<Method, Supplier<Object>> previousPredefinedMethods);

}
