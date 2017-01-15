package logic;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class ConditionTest extends ConditionTestBase {

	public static boolean[] booleans = { true, false };

	@Test
	public void test$conditionalRunRunnable$called() {
		valueSource = () -> true;

		condition.run(() -> responseHolder.setValue(rs));

		assertEquals(rs, responseHolder.getValue());
	}

	@Test
	public void test$conditionalRunRunnable$notCalled() {
		valueSource = () -> false;

		condition.run(() -> responseHolder.setValue(rs));

		assertEquals(null, responseHolder.getValue());
	}

	@Test
	public void test$getNameThrowsException() {
		exception.expect(UnsupportedOperationException.class);
		condition.getName();
	}

	@Theory
	public void test$getValueCalledWhenRunCalled(boolean returnValue) {
		condition = new Condition() {

			@Override
			public boolean getValue(Optional<StringBuilder> osb) {
				osb.ifPresent(sb -> sb.append("I em called"));
				return returnValue;
			}

		};

		condition.run(osb, () -> {
			return;
		});

		assertEquals("I em called", osb.get().toString());
	}

	@Test
	public void test$valueRequested() {
		condition = new Condition() {

			@Override
			public boolean getValue(Optional<StringBuilder> ignoredOSB) {
				osb.get().append(rs);
				return true;
			}

		};

		condition.getValue();

		assertEquals(rs, osb.get().toString());
	}

}
