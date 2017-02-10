package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import testutils.RandomizedTest;
import utils.Behavior;

public class WithMarkersSupportTest implements RandomizedTest {

	private static final class Helper implements WithMarkersSupport<Helper> {

		private final MarkerSupport support = new MarkerSupport();

		@Override
		public MarkerSupport getMetaInfo() {
			return support;
		}
	}

	private String marker;
	private Helper helper;

	@Before
	public final void setUpWithMarkersSupportTestBase() {
		marker = randomString();
		helper = new Helper();
	}

	@Test
	@Behavior("Аннотация позволяет работать с метаинформацией на лету")
	public void test$metaSupportHasHackToUpdateMetaAtDeFly() {
		final StringBuilder testLog = new StringBuilder();
		helper = helper.withMeta(w -> w.mark(marker)).withMeta(w -> testLog.append(w.hasMarker(marker)));
		assertEquals(true, Boolean.valueOf(testLog.toString()));
	}

	@Test
	@Behavior("Маркер с булевым значением можно взвести на лету")
	public void test$booleanMarkerOnFlySeting() {
		helper = helper.mark(marker);
		assertEquals(true, helper.getMetaInfo().hasMarker(marker));
	}

	@Test
	@Behavior("Маркер со значением можно установить на лету")
	public void test$valueMarkersOnFlySetting() {
		helper = helper.mark("one", "ONE").mark("two", "TWO");

		final StringBuilder sb = new StringBuilder();
		sb.append(helper.getMetaInfo().getMarkerValue("one", randomString()));
		sb.append("|");
		sb.append(helper.getMetaInfo().getMarkerValue("two", randomString()));

		assertEquals("ONE|TWO", sb.toString());
	}

}
