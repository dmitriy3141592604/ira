package test.uibuilder;

import org.i2g.ira.uibuilder.Attribute;
import org.i2g.ira.uibuilder.AttributeSerializer;
import org.junit.Before;

import testutils.RandomizedTest;

public abstract class StringAttributeSerializerTestBase implements RandomizedTest {

	private AttributeSerializer serializer;

	private String aName;

	private String aValue;

	private final Attribute attribute = new Attribute() {

		@Override
		public String getName() {
			return aName;
		}

		@Override
		public String getValue() {
			return aValue;
		}
	};

	@Before
	public void setAttribute2StringTransformerTestBase() {
		serializer = new AttributeSerializer();
	}

	/**
	 * Выполняет сериализацию аттрибута
	 */
	protected String transform(String name, String value) {
		aName = name;
		aValue = value;
		return serializer.serialize(attribute);
	}

}
