package logic;

public class ConditionSimple extends ConditionBase {

	private boolean value;

	public ConditionSimple(String name) {
		this(name, false);
	}

	public ConditionSimple(String name, boolean value) {
		super(name);
		this.value = value;
	}

	@Override
	public boolean getValue(StringBuilder log) {
		if (log != null) {
			log.append(name).append(":").append(value);
		}
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	// TODO not tested
	public void setOn() {
		setValue(true);
	}

	// TODO not tested
	public void setOff() {
		setValue(false);
	}
}
