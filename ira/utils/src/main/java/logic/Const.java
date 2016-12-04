package logic;

public class Const {

	public static <T> T wrappedConst(T returnValue, Runnable action) {
		action.run();
		return returnValue;
	}

}
