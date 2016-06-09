package utils;

public class AbstractProcess implements Comparable {

	public int compareTo(Object arg0) {
		return this.getClass().getName().compareTo(arg0.getClass().getName());
	}

}