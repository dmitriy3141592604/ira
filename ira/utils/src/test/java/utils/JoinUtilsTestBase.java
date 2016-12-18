package utils;

import static utils.collections.Collector.newCollector;

import org.junit.Before;

import testutils.RandomizedTest;
import utils.collections.Collector;

public class JoinUtilsTestBase implements RandomizedTest {

	protected Collector<String> items;

	@Before
	public final void setUpJoinUtilsTest() {
		items = newCollector();
	}

}