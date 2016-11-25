package utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ComplaintSaveProcessBaseTest {

	/** Нужно для EMMA **/
	@Test
	public void test$defaultConstructor() {
		final ComplaintSaveProcessBase process = new ComplaintSaveProcessBase() {

		};
		assertNotNull(process);
	}

}
