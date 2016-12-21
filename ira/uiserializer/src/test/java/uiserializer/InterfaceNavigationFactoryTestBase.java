package uiserializer;

import org.junit.Before;

import uiserializer.InterfaceNavigationFactoryTest.HelpInterface;

public abstract class InterfaceNavigationFactoryTestBase {

	protected HelpInterface buildFrom;

	@Before
	public final void setUpInterfaceNavigationFactoryTestBase() {
		buildFrom = new InterfaceNavigationFactory().buildFrom(HelpInterface.class);
	}

}
