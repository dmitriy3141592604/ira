package logic;

import java.util.Optional;

public interface LogicOperator {

	public boolean eval(Optional<StringBuilder> oLog, Condition... conditions);

	public String getName();

}
