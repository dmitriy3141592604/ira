package logic;

import static org.junit.rules.ExpectedException.none;
import static utils.Value.newValue;

import java.util.Optional;
import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import testutils.RandomizedTest;
import utils.Value;

public abstract class ConditionTestBase implements RandomizedTest {

	private final class ConditionBaseTestHelper implements Condition {
		@Override
		public boolean getValue(Optional<StringBuilder> OLog) {
			return valueSource.get();
		}

	}

	@Rule
	public ExpectedException exception = none();

	protected String rs;

	protected Supplier<Boolean> valueSource;

	protected Condition condition;

	protected Value<String> responseHolder;

	protected Optional<StringBuilder> osb;

	@Before
	public final void setUpConditionTestBase() {
		rs = randomString();
		responseHolder = newValue();
		condition = new ConditionBaseTestHelper();
		osb = Optional.of(new StringBuilder());
	}

}