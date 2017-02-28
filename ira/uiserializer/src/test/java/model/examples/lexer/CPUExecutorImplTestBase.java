package model.examples.lexer;

import java.io.LineNumberReader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.Responsibility;

public abstract class CPUExecutorImplTestBase {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Responsibility("Предоставляет доступ до тестируемого класса")
	protected CPUExecutorImpl executor;

	@Responsibility("Предоставляет дотуп до читающего потока")
	protected LineNumberReader reader;

	@Responsibility("Хранит лог выполненных операций")
	protected List<String> testLog;

	@Responsibility("Предоставляет доступ до CPU")
	protected CPU cpu;

	@Responsibility("Доступ к прочитанным токенам")
	protected Queue<Token> parsedTokens;

	protected CPUImpl cpuImpl;

	@Before
	public final void setUpCPUExecutorImplTestBase() {
		testLog = new ArrayList<String>();
		cpu = (CPU) Proxy.newProxyInstance(getClass().getClassLoader(), new Class<?>[] { CPU.class }, this::cpuReflector);
		executor = new CPUExecutorImpl(cpu, newProgram());
		parsedTokens = new LinkedList<Token>();
		cpuImpl = new CPUImpl();
	}

	private ArrayList<CPUInstruction> newProgram() {
		final ArrayList<CPUInstruction> prg = new ArrayList<CPUInstruction>();
		final int ignoreSpaces = 0, acceptNotSpaces = 1;
		prg.add(new CPUInstruction() {

			@Override
			public boolean accept(char ch) {
				return Character.isWhitespace(ch);
			}

			@Override
			public void apply(char ch, CPU cpu) {

			}

			@Override
			public int nextInstruction() {
				return ignoreSpaces;
			}

			@Override
			public void processEof(CPU cpu) {
				final Token pop = cpu.pop();
				if (pop != null) {
					parsedTokens.add(pop);
				}
			}
		});
		prg.add(new CPUInstruction() {

			@Override
			public boolean accept(char ch) {
				return !Character.isWhitespace(ch);
			}

			@Override
			public void apply(char ch, CPU cpu) {
				logger.debug("push: {}", ch);
				cpu.push(ch);
			}

			@Override
			public int nextInstruction() {
				return acceptNotSpaces;
			}

			@Override
			public void processEof(CPU cpu) {
				final Token pop = cpu.pop();
				if (pop != null) {
					parsedTokens.add(pop);
				}
			}
		});
		prg.add(new CPUInstruction() {

			@Override
			public boolean accept(char ch) {
				return Character.isWhitespace(ch);
			}

			@Override
			public void apply(char ch, CPU cpu) {
				parsedTokens.add(cpu.pop());
			}

			@Override
			public int nextInstruction() {
				return ignoreSpaces;
			}

			@Override
			public void processEof(CPU cpu) {
				final Token pop = cpu.pop();
				if (pop != null) {
					parsedTokens.add(pop);
				}
			}

		});
		return prg;
	}

	@Responsibility("Логирует методы, которые были вызвани пр работе Executor")
	protected Object cpuReflector(Object proxy, Method method, Object[] args) {
		testLog.add(method.getName());
		try {
			return method.invoke(cpuImpl, args);
		} catch (final Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	protected void process(String s) {
		reader = new LineNumberReader(new StringReader(s));
		executor.process(reader);
	}

	protected String resultLog() {
		return testLog.toString();
	}

}