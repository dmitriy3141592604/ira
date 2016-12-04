package logic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Test;

public class SampleLogicTest {
	private Condition isPage;
	private Condition isSetter;
	private Condition isGetter;
	private Condition isSubmit;
	private StringBuilder log;
	private Condition printSetter;

	public static class Combinator {

		public static class And extends Combinator {

		}

		public Supplier<Boolean> prorogue(Supplier<Boolean> supplier) {
			return supplier;
		}

		public static Combinator and = new And();

		public boolean eval(StringBuilder log, Condition left, Condition right) {
			log.append("and(");
			final boolean v = left.getValue(log) && prorogue(() -> right.getValue(log.append(","))).get();
			log.append(")");
			return v;
		}

	}

	public static interface Condition {

		Condition and(String string, Condition condition);

		boolean getValue(StringBuilder log);

		public String getName();

		void setValue(boolean b);
	}

	public static class SimpleCondition implements Condition {

		private String name;

		private boolean value;

		public SimpleCondition(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public boolean getValue(StringBuilder log) {
			log.append(name);
			log.append(":");
			log.append(value);
			return value;
		}

		@Override
		public void setValue(boolean value) {
			this.value = value;
		}

		@Override
		public Condition and(String name, Condition condition) {
			final CombinedCondition combinedCondition = new CombinedCondition(Combinator.and, name, this, condition);
			return combinedCondition;
		}

	}

	public static class CombinedCondition implements Condition {

		private final ArrayList<Condition> source;

		private final Combinator combinator;

		private final String name;

		public CombinedCondition(Combinator combinator, String name, Condition one, Condition other) {
			this.combinator = combinator;
			this.name = name;
			this.source = new ArrayList<Condition>();
			this.source.add(one);
			this.source.add(other);

		}

		@Override
		public Condition and(String name, Condition condition) {
			return new CombinedCondition(Combinator.and, name, this, condition);
		}

		@Override
		public boolean getValue(StringBuilder log) {
			boolean retVal = true;
			final Iterator<Condition> iterator = source.iterator();
			final Condition first = iterator.next();
			while (iterator.hasNext()) {
				retVal = combinator.eval(log, first, iterator.next());
			}
			return retVal;
		}

		@Override
		public void setValue(boolean b) {
			throw new UnsupportedOperationException("Can't set value for combined condition. It will only evalyate at runtime");
		}

		@Override
		public String getName() {
			return name;
		}
	}

	@Before
	public void setUpSampleLogicTestBase() {
		isPage = new SimpleCondition("isPage");
		isSetter = new SimpleCondition("isSetter");
		isGetter = new SimpleCondition("isGetter");
		isSubmit = new SimpleCondition("isSubmit");

		printSetter = isPage.and("printSetter", isSetter);

		log = new StringBuilder();
	}

	@Test
	public void test$001() {
		final Condition printSetter = isPage.and("printSetter", isSetter);
		assertEquals(false, printSetter.getValue(new StringBuilder()));
	}

	@Test
	public void test$002() {
		isPage.setValue(true);
		isSetter.setValue(true);

		assertEquals(true, printSetter.getValue(new StringBuilder()));
	}

	@Test
	public void test$simpleEvidence$true() {
		isPage.setValue(true);
		isPage.getValue(log);
		assertEquals("isPage:true", log.toString());
	}

	@Test
	public void test$simpleEvidence$false() {
		isPage.setValue(false);
		isPage.getValue(log);
		assertEquals("isPage:false", log.toString());
	}

	@Test
	public void test$combined$simplest$checkLazy() {
		printSetter.getValue(log);
		assertEquals("and(isPage:false)", log.toString());
	}

	@Test
	public void test$combined$simplest$andEvaluatedToFirstNotFalseValue() {
		isPage.setValue(true);
		isSetter.setValue(true);
		printSetter.getValue(log);
		assertEquals("and(isPage:true,isSetter:true)", log.toString());
	}
}
