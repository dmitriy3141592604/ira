package test.uibuilder;

import org.junit.Before;

import testutils.RandomizedTest;
import utils.IraTest;

public abstract class StringAttributeSerializerTestBase extends IraTest implements RandomizedTest {

	private StringAttributeSerializer serializer;

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
		serializer = new StringAttributeSerializer();
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
