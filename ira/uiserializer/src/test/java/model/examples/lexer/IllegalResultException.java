package model.examples.lexer;

import utils.Responsibility;

@Responsibility("Оповещает о неправильном результате")
public class IllegalResultException extends RuntimeException {

	private static final long serialVersionUID = 3991830835128462719L;

}
