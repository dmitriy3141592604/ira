package utils;

import static org.junit.Assert.assertNotNull;
import static utils.ExceptionSupplier.hideSupplierExceptions;

import org.junit.Test;

public class ExceptionSupplierTest {

	public static class ExceptionHelper {

		public ExceptionHelper() throws Exception {

		}

	}

	@Test
	public void test$hideHelperUse() {
		assertNotNull(hideSupplierExceptions(() -> new ExceptionHelper()));
	}

}
