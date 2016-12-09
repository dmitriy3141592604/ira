package uiserializer.components;

public abstract class CheckBox implements Cmp {

	protected String label;

	protected Boolean initValue;

	void label(String label) {
		this.label = label;
	}

	void initValue(Boolean initValue) {
		this.initValue = initValue;
	}
}