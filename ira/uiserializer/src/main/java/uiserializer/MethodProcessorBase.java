package uiserializer;

import static utils.Safer.safe;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Supplier;

public abstract class MethodProcessorBase implements InvocationHandler {

	protected Map<Method, Supplier<Object>> previousPredefinedMethods;

	protected FromClassBuilder fromClassBuilder;

	protected Map<Method, Supplier<Object>> predefinedMethods;

	protected Class<?> executedMethodOwnerClass;

	protected Object[] currentArgs;

	protected Method currentMethod;

	protected Object currentProxy;

	protected Class<?> invokMethodReturnClass;

	protected abstract Object invoce() throws Exception;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		this.currentProxy = proxy;
		this.currentMethod = method;
		this.currentArgs = args;
		this.invokMethodReturnClass = currentMethod.getReturnType();

		return safe(this::invoce);
	}

	public void setPreviousPredefinedMethods(Map<Method, Supplier<Object>> previousPredefinedMethods) {
		this.previousPredefinedMethods = previousPredefinedMethods;
	}

	public void setFromClassBuilder(FromClassBuilder fromClassBuilder) {
		this.fromClassBuilder = fromClassBuilder;
	}

	public void setPredefinedMethods(Map<Method, Supplier<Object>> predefinedMethods) {
		this.predefinedMethods = predefinedMethods;
	}

	public void setExecutedMethodOwnerClass(Class<?> executedMethodOwnerClass) {
		this.executedMethodOwnerClass = executedMethodOwnerClass;
	}

}
