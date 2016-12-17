package uiserializer;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import testutils.RandomizedTest;

@RunWith(MockitoJUnitRunner.class)
public abstract class UIBuilderBuilderTestBase implements RandomizedTest, Mockitor {

	protected UIBuilderBuilder bb;

	@Before
	public final void setUpUIBuilderBuilderTestBase() {
		bb = new UIBuilderBuilder();
	}
}
