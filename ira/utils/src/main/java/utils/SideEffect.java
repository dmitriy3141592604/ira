package utils;

@Responsibility("Предоставляет возможность привязать действие к операции присваивани")
public class SideEffect {

	public static <ReturnValue> ReturnValue withEffect(ReturnValue returnValue, Runnable sideEffect) {
		sideEffect.run();
		return returnValue;
	}
}