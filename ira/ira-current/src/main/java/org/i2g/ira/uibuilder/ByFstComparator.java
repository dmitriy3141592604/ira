package org.i2g.ira.uibuilder;

import java.util.Comparator;

import utils.Triple;

public class ByFstComparator implements Comparator<Triple<Character, String, String>> {

	private static ByFstComparator instance = new ByFstComparator();

	public static ByFstComparator byFstComparatorInstance() {
		return instance;
	}

	@Override
	public int compare(Triple<Character, String, String> o1, Triple<Character, String, String> o2) {
		return o1.getFst().compareTo(o2.getFst());
	}
}