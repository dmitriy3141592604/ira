package logic.condition;

public abstract class NamedCondition implements Condition {

	private String name;

	protected NamedCondition(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
