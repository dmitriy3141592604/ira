package model.examples.lexer;

import static utils.Quietly.quietly;

import java.io.LineNumberReader;
import java.util.List;

public class CPUExecutorImpl {

	private final CPU cpu;

	private int instructionPointer = 0;

	private final List<CPUInstruction> program;

	public CPUExecutorImpl(CPU cpu, List<CPUInstruction> program) {
		this.cpu = cpu;
		this.program = program;
	}

	public void process(LineNumberReader reader) {
		quietly(() -> {
			String line = null;
			while (null != (line = reader.readLine())) {
				cpu.lineFeed();
				for (int i = 0; i < line.length(); ++i) {
					while (!program.get(instructionPointer).accept(line.charAt(i))) {
						instructionPointer++;
					}
					final CPUInstruction accepingInstruction = program.get(instructionPointer);
					accepingInstruction.apply(line.charAt(i), cpu);
					instructionPointer = accepingInstruction.nextInstruction();
				}
			}
			program.get(instructionPointer).processEof(cpu);
			cpu.eof();
		});
	}

}
