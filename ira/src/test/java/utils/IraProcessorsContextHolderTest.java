package utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class IraProcessorsContextHolderTest {

	@Test // expected for EMMA
	public void test$instance() {
		assertNotNull(new IraProcessorsContextHolder() {
		});
	}
}
