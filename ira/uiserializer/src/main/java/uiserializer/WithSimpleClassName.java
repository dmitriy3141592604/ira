package uiserializer;

import application.support.WithName;
import utils.Responsibility;

@Responsibility("Добавляет производному классу возможность сказать свое простое имя")
public interface WithSimpleClassName extends WithName {

	@Override
	default String name() {
		return getClass().getSimpleName();
	}

}
