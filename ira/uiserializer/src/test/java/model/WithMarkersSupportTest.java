package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import testutils.RandomizedTest;
import utils.Behavior;

public class WithMarkersSupportTest implements RandomizedTest {

	private static final class Worker extends MarkerSupport implements WithMarkersSupport<Worker> {

		@Override
		public MarkerSupport getMetaInfo() {
			return this;
		}
	}

	private String marker;

	@Before
	public final void setUpWithMarkersSupportTestBase() {
		marker = randomString();
	}

	@Test
	@Behavior("Аннотация позволяет работать с метаинформацией на лету")
	public void test$metaSupportHasHackToUpdateMetaAtDeFly() {
		final StringBuilder testLog = new StringBuilder();
		new Worker().withMeta(w -> w.mark(marker)).withMeta(w -> testLog.append(w.hasMarker(marker)));
		assertEquals(true, Boolean.valueOf(testLog.toString()));
	}

}
