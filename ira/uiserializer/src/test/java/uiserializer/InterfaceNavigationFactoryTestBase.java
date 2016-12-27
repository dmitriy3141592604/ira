package uiserializer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import uiserializer.InterfaceNavigationFactoryTest.HelpInterface;

public abstract class InterfaceNavigationFactoryTestBase {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	protected HelpInterface buildFrom;

	@Before
	public final void setUpInterfaceNavigationFactoryTestBase() {
		buildFrom = new InterfaceNavigationFactory().buildFrom(HelpInterface.class);
	}

}
