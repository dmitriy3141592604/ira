package logic;

public abstract class ConditionBase implements Condition {

	protected final String name;

	protected ConditionBase(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
