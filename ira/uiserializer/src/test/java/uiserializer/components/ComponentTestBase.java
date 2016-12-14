package uiserializer.components;

import java.io.File;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

// FIXME Восстанови меня !
public abstract class ComponentTestBase {

	@Rule
	public TestName name = new TestName();

	// protected UIBuilderFactoryBuilder builder;

	protected Set<String> attributeValues;

	protected Set<String> tagNames;

	@Before
	public final void setUpComponentTestBase() {
		// builder = new UIBuilderFactoryBuilder();
		// builder.build();
		// attributeValues = builder.getAttributeValues();
		// tagNames = builder.getTagNames();
	}

	@After
	public final void tearDown() {
		final String simpleOutputFileName = this.getClass() + "$" + name.getMethodName();
		final File outputFileName = new File("html/" + simpleOutputFileName + ".html");

		// new OnFileWriter(outputFileName).accept((out) ->
		// out.print(builder.getSb()));
	}

	// protected String serializeContentToInternalBuffer(boolean b) {
	// final String retVal = builder.serializeContentToInternalBuffer();
	// if (b) {
	// System.out.println(retVal);
	// }
	// return retVal;
	// }

}