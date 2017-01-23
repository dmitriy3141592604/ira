package application;

import application.support.Name;
import application.support.WithName;
import utils.Responsibility;

@Responsibility("Извлекает имя объекта из аннотации")
public interface WithAnnotationBasedName extends WithName {

	@Override
	default String name() {
		final Class<? extends WithAnnotationBasedName> derivedClass = this.getClass();
		final Name annotation = derivedClass.getAnnotation(Name.class);
		if (annotation != null) {
			return annotation.value();
		}
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Производный класс: '");
		stringBuilder.append(derivedClass);
		stringBuilder.append("' не содержит аннотации: '");
		stringBuilder.append(Name.class);
		stringBuilder.append("'");
		throw new IllegalStateException(stringBuilder.toString());

	}

}
