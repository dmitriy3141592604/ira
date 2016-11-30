package userstories.actions;

import java.util.Observer;

public interface NamedVariable {

	public void setVariableName(String variableName);

	public void setActionDescription(String actionDescription);

	public void setOwner(Observer observer);

}
