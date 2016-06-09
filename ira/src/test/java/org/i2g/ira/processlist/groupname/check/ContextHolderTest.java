package org.i2g.ira.processlist.groupname.check;

import static org.junit.Assert.assertSame;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import utils.IraProcessorsContextHolder;

public class ContextHolderTest {

	@Test
	public void test() {
		IraProcessorsContextHolder ch = new IraProcessorsContextHolder();
		LinkedList<String> array = new LinkedList<String>();
		ch.setValue(array);
		List<String> value = ch.getValue();

		assertSame(array, value);

	}
}
