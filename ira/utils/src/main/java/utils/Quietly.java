package utils;

import utils.io.ExceptionRunnable;

@Responsibility("Синтаксический сахар. Позволяет выполнить блок кода без try catch")
public class Quietly {

	public static void quietly(ExceptionRunnable object) {
		object.quietly();
	}

}