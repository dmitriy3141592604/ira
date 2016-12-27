package uiserializer;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterfaceNavigationFactory extends InterfaceNavigationFactoryBase implements FromClassBuilder {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public <T> T buildFrom(Class<T> executedMethodOwnerClass) {
		return buildFrom(executedMethodOwnerClass, new HashMap<Method, Supplier<Object>>());
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T buildFrom(Class<T> executedMethodOwnerClass, Map<Method, Supplier<Object>> previousPredefinedMethods) {

		logger.trace("Start processing: {}", executedMethodOwnerClass);

		final List<Class<?>> executedMethodReturntypeClassList = new ArrayList<Class<?>>();
		final HashMap<Method, Supplier<Object>> predefinedMethods = new HashMap<Method, Supplier<Object>>();
		executedMethodReturntypeClassList.add(executedMethodOwnerClass);

		final MethodProcessor methodCallHandler = new MethodProcessor();

		methodCallHandler.setPreviousPredefinedMethods(previousPredefinedMethods);
		methodCallHandler.setFromClassBuilder(this);
		methodCallHandler.setPredefinedMethods(predefinedMethods);
		methodCallHandler.setExecutedMethodOwnerClass(executedMethodOwnerClass);

		final Class<?>[] executedMethodReturnTypeClassesArray = executedMethodReturntypeClassList.toArray(new Class<?>[0]);

		return (T) Proxy.newProxyInstance(classLoader, executedMethodReturnTypeClassesArray, methodCallHandler);
	}

}
