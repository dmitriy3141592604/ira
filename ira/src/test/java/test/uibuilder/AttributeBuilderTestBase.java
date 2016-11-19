package test.uibuilder;

import org.i2g.ira.uibuilder.Attribute;
import org.i2g.ira.uibuilder.StringAttributeSerializer;
import org.junit.Before;

import utils.IraTest;

public class AttributeBuilderTestBase extends IraTest {

	protected AttributeBiulderTestTagHelper attrs;

	protected String transform(Attribute attr) {
		final StringAttributeSerializer serializer = new StringAttributeSerializer();
		return serializer.serialize(attr);
	}

	@Before
	public final void setUpAttributeBuilderTestBase() {
		attrs = new AttributeBiulderTestTagHelper() {
		};
	}

}
