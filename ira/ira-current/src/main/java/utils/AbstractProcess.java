package utils;

public class AbstractProcess implements Comparable<Object> {

	@Override
	public int compareTo(Object right) {
		if (right == null) {
			throw new IllegalArgumentException("Null not allowed for comparsion");
		}
		return this.getClass().getName().compareTo(right.getClass().getName());
	}

}