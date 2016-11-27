package org.i2g;

import static org.junit.Assert.assertEquals;

import org.i2g.ira.FileDaoImpl;
import org.junit.Test;

import testutils.RandomizedTest;
import utils.IraTest;

public class FileDaoImplTest extends IraTest implements RandomizedTest {

	@Test
	public void test$constantReturned() {
		assertEquals(49, new FileDaoImpl().sizeByName(randomString()).intValue());
	}

}
