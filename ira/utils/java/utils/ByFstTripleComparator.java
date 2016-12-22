package utils;

import java.util.Comparator;

import utils.Triple;

public class ByFstTripleComparator implements Comparator<Triple<Character, String, String>> {

	private static ByFstTripleComparator instance = new ByFstTripleComparator();

	public static ByFstTripleComparator byFstComparatorInstance() {
		return instance;
	}

	@Override
	public int compare(Triple<Character, String, String> o1, Triple<Character, String, String> o2) {
		return o1.getFst().compareTo(o2.getFst());
	}
}