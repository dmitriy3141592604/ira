package uiserializer;

public interface SelfAware<T> {

	@SuppressWarnings("unchecked")
	default T self() {
		return (T) this;
	}
}
