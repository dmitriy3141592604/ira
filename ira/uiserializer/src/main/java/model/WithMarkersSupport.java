package model;

import java.util.function.Consumer;

import utils.Responsibility;

@Responsibility("Фиксирует возможность доступа к мета информации об объекте")
public interface WithMarkersSupport<T extends WithMarkersSupport<T>> {

	/** Обеспечивает доступ к метаинформации об объекте **/
	MarkerSupport getMetaInfo();

	@SuppressWarnings("unchecked")
	@Responsibility("Реализует возможность добавления меток на лету")
	default T withMeta(Consumer<MarkerSupport> c) {
		c.accept(getMetaInfo());
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	@Responsibility("Упрощает добавление булевого маркера")
	default T mark(String marker) {
		getMetaInfo().mark(marker);
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	@Responsibility("Упрощает установку маркеров со значением")
	default T mark(String marker, Object value) {
		getMetaInfo().markWith(marker, value);
		return (T) this;
	}
}
