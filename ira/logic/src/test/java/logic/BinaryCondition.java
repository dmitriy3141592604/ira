package logic;

public abstract class BinaryCondition extends NamedCondition {

	protected BinaryCondition(String name) {
		super(name);
	}

	protected CombinedCondition combinate(Combinator combinator, String name, Condition condition) {
		return new CombinedCondition(combinator, name, this, condition);
	}

}
