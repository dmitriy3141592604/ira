package model.examples.lexer;

import utils.Responsibility;

@Responsibility("Предоставляет интерфейс исполняемой инструкции CPU")
public interface CPUInstruction {

	@Responsibility("Отвечает за разрешение применения инструкции")
	boolean accept(char ch);

	@Responsibility("Применяет символ")
	void apply(char ch, CPU cpu);

	@Responsibility("Указывает следующую инструкцию")
	int nextInstruction();

	@Responsibility("Отвечает за обработку конца входного потока")
	void processEof(CPU cpu);

}
