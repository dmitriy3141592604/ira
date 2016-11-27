package org.i2g.ira.processlist.groupname.check;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import utils.Behavior;
import utils.IraProcessorsContextHolder;

public class ContextHolderTest {

	@Behavior("Значения можно хранить в глобальном контексте")
	@Test
	public void test$simpleSetPropertyFromOneTread() {
		final LinkedList<String> array = new LinkedList<String>();
		IraProcessorsContextHolder.setValue(array);
		final List<String> value = IraProcessorsContextHolder.getValue();

		assertSame(array, value);
	}

	@Behavior("Каждый поток имеет собственнцй контекст")
	@Test(timeout = 1000)
	public void test$EachThreadHasOwnValue() throws Exception {
		final Object allowWrite = new Object();
		final Object allowRead = new Object();
		final StringBuilder log = new StringBuilder();
		final List<Long> mainTreadSetValueTime = new ArrayList<Long>();
		final List<Long> subProcessSetValueTime = new ArrayList<Long>();
		final Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (allowWrite) {
					IraProcessorsContextHolder.setValue("sub process");
					subProcessSetValueTime.add(System.nanoTime());
				}

				synchronized (allowRead) {
					log.append((String) IraProcessorsContextHolder.getValue());
				}
			}

		});

		synchronized (allowRead) {
			synchronized (allowWrite) {
				IraProcessorsContextHolder.setValue("main process");
				mainTreadSetValueTime.add(System.nanoTime());
				thread.start();
			}
		}

		thread.join();

		assertEquals("sub process", log.toString());
		assertEquals("main process", IraProcessorsContextHolder.getValue());
		final Long mainAssignTime = mainTreadSetValueTime.get(0);
		final Long subAssignTime = subProcessSetValueTime.get(0);
		assertTrue("times is main: " + mainAssignTime + " sub: " + subAssignTime, mainAssignTime < subAssignTime);
	}
}
