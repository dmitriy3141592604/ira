package forth;

import java.util.ArrayList;

public class FStack {

	private final ArrayList<FToken> impl = new ArrayList<>();

	public void push(FToken token) {
		impl.add(token);
	}

	public FToken pop() {
		return impl.remove(impl.size() - 1);
	}

	public boolean isEmpty() {
		return impl.isEmpty();
	}

	public FToken top() {
		return impl.get(impl.size() - 1);
	}

}