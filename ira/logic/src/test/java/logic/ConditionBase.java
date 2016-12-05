package logic;

public abstract class ConditionBase implements Condition {

	private final String name;

	protected ConditionBase(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
