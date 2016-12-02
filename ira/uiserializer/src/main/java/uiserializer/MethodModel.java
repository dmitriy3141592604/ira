package uiserializer;

import static utils.Safer.safe;
import static utils.assertions.NotNullGuard.guard;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import utils.ExceptionSupplier;

public class MethodModel {

	private final Method method;

	public MethodModel(ExceptionSupplier<Method> methodSupplier) {
		this(safe(methodSupplier));
	}

	public MethodModel(Method method) {
		this.method = method;
	}

	public <A extends Annotation> A from(Class<A> annotationMarker) {
		return guard(method.getAnnotation(annotationMarker), "No annotation %s present in %s", annotationMarker, method);
	}

	public String getName() {
		return method.getName();
	}

	public Class<?> getReturnType() {
		return method.getReturnType();
	}

	public TypeNavigator getReturnTypeNavigator() {
		return new TypeNavigator(method.getReturnType());
	}
}