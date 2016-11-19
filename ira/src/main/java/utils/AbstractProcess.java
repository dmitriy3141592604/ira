package utils;

public class AbstractProcess implements Comparable<Object> {

	@Override
	public int compareTo(Object right) {
		return this.getClass().getName().compareTo(right.getClass().getName());
	}

}