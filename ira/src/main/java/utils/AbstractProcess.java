package utils;

public class AbstractProcess implements Comparable {

	public int compareTo(Object right) {
		return this.getClass().getName().compareTo(right.getClass().getName());
	}

}