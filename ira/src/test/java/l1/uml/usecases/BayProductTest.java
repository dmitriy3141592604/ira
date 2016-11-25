package l1.uml.usecases;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public abstract class BayProductTest {

	@Test
	public final void test$instantiate() {
		assertNotNull(new BayProduct());
	}
}
