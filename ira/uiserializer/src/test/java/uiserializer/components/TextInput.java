package uiserializer.components;

public abstract class TextInput implements Component {

	protected String label;

	protected String initValue;

	void label(String label) {
		this.label = label;
	}

	void initValue(String initValue) {
		this.initValue = initValue;
	}

}