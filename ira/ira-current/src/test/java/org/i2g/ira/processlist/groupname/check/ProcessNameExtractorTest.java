package org.i2g.ira.processlist.groupname.check;

import static org.i2g.ira.utils.ProcessNameExtractor.newProcessNameExtractor;
import static org.junit.Assert.assertEquals;

import org.i2g.ira.utils.ProcessNameExtractor;
import org.junit.Test;

import testutils.RandomizedTest;
import utils.IraTest;

public class ProcessNameExtractorTest extends ProcessNameExtractorTestBase {

	@Test
	public void test$correctData() {
		assertEquals("lang.reflect", extract("java", "java.lang.reflect.Proxy"));
	}

	@Test
	public void test$correctData$secondVariat() {
		assertEquals("awt.image", extract("java.some", "java.some.awt.image.BufferedProxy"));
	}

	@Test
	public void test$nullablePrefixesNotAllowed() {
		exception.expect(IllegalArgumentException.class);
		extract(null, randomString());
	}

	@Test
	public void test$emptyPackageNotAllowed() {
		except(IllegalArgumentException.class);
		extract("", "java.lang.reflect.Proxy");
	}

	@Test
	public void test$moreThanClassNameItemsNotAllowed() {
		exception.expect(IllegalArgumentException.class);
		extract("java", "java.one.two.three.banana");
	}

	@Test
	public void test$packagesRepresentedAsEmptyStringIsNotAlowed$1() {
		exception.expect(IllegalArgumentException.class);
		extract(".", "java.lang.reflect.Proxy");
	}

	@Test
	public void test$packagesRepresentedAsEmptyStringIsNotAlowed$2() {
		exception.expect(IllegalArgumentException.class);
		extract("java.", "java.lang.reflect.Proxy");
	}

	@Test
	public void test$packagesRepresentedAsEmptyStringIsNotAlowed$3() {
		exception.expect(IllegalArgumentException.class);
		extract(".lang", "java.lang.reflect.Proxy");
	}

}

abstract class ProcessNameExtractorTestBase extends IraTest implements RandomizedTest {

	protected String extract(String prefix, String className) {
		final ProcessNameExtractor extractor = newProcessNameExtractor(prefix);
		return extractor.getProcessName(className);
	}

}