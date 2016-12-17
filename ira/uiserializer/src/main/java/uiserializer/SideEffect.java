package uiserializer;

// TODO move to utils
class SideEffect {
	public static <ReturnValue> ReturnValue withEffect(ReturnValue returnValue, Runnable sideEffect) {
		sideEffect.run();
		return returnValue;
	}
}