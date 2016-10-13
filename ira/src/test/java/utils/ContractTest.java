package utils;

import static utils.Tuple.newTuple;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;

public class ContractTest extends ContractTestBase {

	private Range v;

	public class Range {

		/** Начало диапазона **/
		public String begin;

		/** Окончание диапазона **/
		public String end;
	}

	public static class RangeContract extends ContractBase<Range> {

		{
			$("Диапазон должен иметь начало", value -> checkNotNull(value.begin));
			$("Диапазон должен иметь окончание ${end}", value -> checkNotNull(value.end));
			$("Начало диапазона должно быть меньше окончания: [${begin},${end}]", value -> lessThat(value.begin, value.end));
		}
	};

	public static abstract class ContractBase<ObjectForValidation> implements Contract<ObjectForValidation> {

		private final List<Tuple<String, Consumer<ObjectForValidation>>> asserts = new LinkedList<>();

		protected void $(String name, Consumer<ObjectForValidation> consumer) {
			final Consumer<ObjectForValidation> wrapper = new Consumer<ObjectForValidation>() {

				@Override
				public void accept(ObjectForValidation t) {
					try {
						consumer.accept(t);
					} catch (final ContractFailException contractFailException) {
						final String formattedMessage = name.replaceAll("[$][{]\\w+[}]", "101");
						throw new ContractFailException(formattedMessage);
					}
				}

			};
			asserts.add(newTuple(name, wrapper));
		}

		@Override
		public void validate(ObjectForValidation v) {
			asserts.forEach((rule) -> rule.getSnd().accept(v));
		}

		public void checkNotNull(String begin) {
			bum(begin == null);
		}

		public void lessThat(String left, String right) {
			bum(0 < left.compareTo(right));
		}

		protected void bum(boolean condition) {
			if (condition) {
				throw new ContractFailException(null);
			}
		}

	}

	@Before
	public final void setUpContractTest() {
		v = new Range();
		v.begin = "a";
		v.begin = "b";
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Contract<Range> createValidator() {
		return new RangeContract();
	}

	@Test
	@Behavior("Контракт прменим к валидным объектам")
	public void test() {
		validate(v);
	}

	/** TODO Исключение должно быть хитрым **/
	@Test
	@Behavior("При нарушении контракта возникает исключительная ситуация")
	public void test$brokenContract() {
		v.begin = null;
		exception.expect(ContractFailException.class);
		validate(v);
	}

}
