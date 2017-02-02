package expressions;

import static org.junit.Assert.assertEquals;
import static utils.Value.newValue;

import java.util.function.Supplier;

import org.junit.Test;

import utils.Value;
import utils.collections.Collector;

public class ExpressionsTest {

	public interface ValueChangeListener<T> {

		void accept(T newValue);
	}

	public static class Expression<T> {

		private final Collector<ValueChangeListener<T>> listeners = Collector.newCollector();

		private T value;

		public Expression(T value) {
			setValue(value);
		}

		public final void setValue(T value) {
			if ((value.equals(this.value))) {
				return;
			}
			this.value = value;
			listeners.forEach(vcl -> vcl.accept(value));
		}

		public T getValue() {
			return value;
		}

		public <U> U withValue(Expression<U> expression) {
			// expression.addValueChangeListener(expression);
			return expression.getValue();
		}

		public final void addValueChangeListener(ValueChangeListener<T> listener) {
			listeners.remember(listener);
		}

	}

	public class StringExpression extends Expression<String> {

		public StringExpression(String value) {
			super(value);
		}

		public StringExpression add(StringExpression foo) {
			final Supplier<String> summ = () -> this.getValue() + "" + foo.getValue();
			final StringExpression retVal = new StringExpression(summ.get());
			this.addValueChangeListener(x -> retVal.setValue(summ.get()));
			foo.addValueChangeListener(x -> retVal.setValue(summ.get()));
			return retVal;
		}

	}

	@Test
	public void test$valueChangeListener() {

		final StringExpression foo = new StringExpression("foo");
		final StringExpression bar = new StringExpression("bar");

		final StringExpression fooBar = foo.add(bar);

		final Value<String> value = newValue();
		fooBar.addValueChangeListener((newValue) -> value.setValue(newValue));

		foo.setValue("Foo");

		assertEquals("Foobar", value.getValue());

		bar.setValue("Bar");

		assertEquals("FooBar", value.getValue());

	}

}
