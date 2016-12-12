package uiserializer;

public class InterfaceNavigationHistory {

	private final HistoryItem root;

	public InterfaceNavigationHistory() {
		this(null);
	}

	public InterfaceNavigationHistory(HistoryItem root) {
		this.root = root;
	}

}
