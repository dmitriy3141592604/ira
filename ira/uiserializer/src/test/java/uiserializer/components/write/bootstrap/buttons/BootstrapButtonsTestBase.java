package uiserializer.components.write.bootstrap.buttons;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import uiserializer.components.Component;
import uiserializer.components.ComponentTestBase;

public abstract class BootstrapButtonsTestBase<C extends Component> extends ComponentTestBase<C> {

	@Rule
	public TestName name = new TestName();

	@Before
	public void setUpBootstrapButtonsTestBase() {

	}

}
