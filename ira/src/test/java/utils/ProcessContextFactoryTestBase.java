package utils;

import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ProcessContextFactoryTestBase extends IraTest {

	protected ProcessContextFactory factory;

	protected String randomName;

	@Before
	public void setUpProcessContextFactoryBase() {
		randomName = "" + new Date().getTime();
		factory = new ProcessContextFactory();
	}

}
