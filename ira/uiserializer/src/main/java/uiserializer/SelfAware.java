package uiserializer;

import utils.Responsibility;

@Responsibility("Предоставляет доступ до поля this внутри замыканий.")
public interface SelfAware<T> {

	@SuppressWarnings("unchecked")
	default T self() {
		return (T) this;
	}
}
