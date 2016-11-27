package test.uibuilder;

import org.i2g.ira.uibuilder.Attribute;
import org.i2g.ira.uibuilder.AttributeSerializer;
import org.junit.Before;

import utils.IraTest;

public class AttributeBuilderTestBase extends IraTest {

	protected AttributeBiulderTestTagHelper attrs;

	protected String transform(Attribute attr) {
		final AttributeSerializer serializer = new AttributeSerializer();
		return serializer.serialize(attr);
	}

	@Before
	public final void setUpAttributeBuilderTestBase() {
		attrs = new AttributeBiulderTestTagHelper() {
		};
	}

}
