package uiserializer;

import java.lang.reflect.Method;

import application.support.Name;
import application.support.WithId;
import application.support.WithName;

public class MethodProcessor extends MethodProcessorBase {

	@Override
	protected Object invoce() throws Exception {
		if (this.previousPredefinedMethods.containsKey(currentMethod)) {
			return previousPredefinedMethods.get(currentMethod).get();
		}

		if (String.class.isAssignableFrom(invokMethodReturnClass)) {
			return new String("");
		}

		if ("void".equals(invokMethodReturnClass.getName())) {
			return null;
		}

		if (WithId.class.isAssignableFrom(invokMethodReturnClass)) {
			final Method idMethod = invokMethodReturnClass.getMethod("id");
			final String id = executedMethodOwnerClass.getSimpleName() + "." + currentMethod.getName();
			predefinedMethods.put(idMethod, () -> id);
		}

		if (WithName.class.isAssignableFrom(invokMethodReturnClass)) {
			final Method nameMethod = invokMethodReturnClass.getMethod("name");
			final Name annotation = currentMethod.getAnnotation(Name.class);
			if (annotation == null) {
				final StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("No name present in method: ");
				stringBuilder.append(currentMethod.getName());
				stringBuilder.append(" of class ");
				stringBuilder.append(executedMethodOwnerClass);
				throw new IllegalStateException(stringBuilder.toString());
			}
			final String name = annotation.value();
			predefinedMethods.put(nameMethod, () -> name);
		}
		return fromClassBuilder.buildFrom(invokMethodReturnClass, predefinedMethods);
	}

}