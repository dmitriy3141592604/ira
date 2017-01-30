package structure;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MetaSupportTest extends MetaSupportTestBase {

	@Test
	public void test$metaPresent() {
		mt.annotate("marked");
		assertEquals(true, mt.hasMeta("marked"));
	}

	@Test
	public void test$notExistsMetaIsFalse() {
		assertEquals(false, mt.hasMeta("not exists meta"));
	}

	@Test
	public void test$markWithValue() {
		mt.annotate("value meta", "value");
		assertEquals("value", mt.meta("value meta", "_"));
	}

	@Test
	public void test$markWithValue$defaultValue() {
		assertEquals("_", mt.meta("value meta", "_"));
	}

	@Test
	public void test$overwriteAllowedOnlyForEqualsValues$vanila() {
		mt.annotate("value", new AlwaysEqual());
		mt.annotate("value", new AlwaysEqual());
	}

	@Test(expected = IllegalStateException.class)
	public void test$ifValueIsNotEqualPrevious$overwriteNotAllowed() {
		mt.annotate("value", new AlwaysNotEquals());
		mt.annotate("value", new AlwaysNotEquals());
	}

}
