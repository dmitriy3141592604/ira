package uiserializer;

import java.lang.reflect.Method;

import application.support.Label;
import application.support.Name;
import application.support.WithId;
import application.support.WithLabel;
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

		if (invocedMethodReturnClassAssignableTo(WithId.class)) {
			final Method idMethod = invocedMethodReturnClassMethod("id");
			final String id = executedMethodOwnerClass.getSimpleName() + "." + currentMethod.getName();
			predefinedMethods.put(idMethod, () -> id);
		}

		// TODO name и label одинаковы. Нужен рефакторинг
		if (invocedMethodReturnClassAssignableTo(WithName.class)) {
			final Method nameMethod = invocedMethodReturnClassMethod("name");
			final Class<Name> nameAnnotation = Name.class;

			final Name annotation = currentMethod.getAnnotation(nameAnnotation);
			if (annotation == null) {
				throw newNoNameAnnotationPresetException(nameAnnotation);
			}
			final String name = annotation.value();
			predefinedMethods.put(nameMethod, () -> name);
		}

		if (invocedMethodReturnClassAssignableTo(WithLabel.class)) {
			final Method nameMethod = invocedMethodReturnClassMethod("label");
			final Class<Label> labelAnnotation = Label.class;

			final Label annotation = currentMethod.getAnnotation(labelAnnotation);
			if (annotation == null) {
				throw newNoNameAnnotationPresetException(labelAnnotation);
			}
			final String label = annotation.value();
			logger.info("Получена метка: {}", label);
			predefinedMethods.put(nameMethod, () -> label);
		}

		return fromClassBuilder.buildFrom(invokMethodReturnClass, predefinedMethods);
	}

	private Method invocedMethodReturnClassMethod(String string) throws NoSuchMethodException {
		return invokMethodReturnClass.getMethod(string);
	}

	private boolean invocedMethodReturnClassAssignableTo(Class<?> baseClass) {
		return baseClass.isAssignableFrom(invokMethodReturnClass);
	}

	private IllegalStateException newNoNameAnnotationPresetException(Class<?> nameAnnotation) {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("No ");
		stringBuilder.append(nameAnnotation);
		stringBuilder.append(" present in method: ");
		stringBuilder.append(currentMethod.getName());
		stringBuilder.append(" of class ");
		stringBuilder.append(executedMethodOwnerClass);
		return new IllegalStateException(stringBuilder.toString());
	}

}