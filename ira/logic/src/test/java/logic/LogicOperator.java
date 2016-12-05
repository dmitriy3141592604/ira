package logic;

public interface LogicOperator {

	public boolean eval(StringBuilder log, Condition... conditions);

	public String getName();

}
