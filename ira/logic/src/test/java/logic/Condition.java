package logic;

public interface Condition {

	Condition and(String conditionName, Condition condition);

	Condition or(String conditionName, Condition isGetter);

	boolean getValue(StringBuilder log);

	public String getName();

	default void setValue(boolean b) {
		throw new UnsupportedOperationException("Can't set value for combined condition. It will only evalyate at runtime");
	};

}