package org.i2g.ira.uibuilder;

public class AttributeHelper {

	public static Attribute newAttribute(String name) {
		return newAttribute(name, null);

	}

	public static Attribute newAttribute(String name, String value) {
		return new Attribute() {

			@Override
			public String getName() {
				return name;
			}

			@Override
			public String getValue() {
				return value;
			}

		};
	}

}
