package forth;

public class SForth {

	private final FStack stack = new FStack();

	public void applay(FToken token) {
		token.applay(stack);
	}

}