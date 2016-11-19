package utils;

public interface Process<T> extends Comparable<T> {
	public void execute(T t);
}
