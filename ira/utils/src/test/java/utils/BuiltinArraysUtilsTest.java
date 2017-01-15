package utils;

import static org.junit.Assert.assertEquals;
import static utils.BuiltinArraysUtils.isBuiltinArray;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Enclosed.class)
public class BuiltinArraysUtilsTest {

	@RunWith(Parameterized.class)
	public static class DescriminationTest {
		public static class Descriminator {

			private final boolean isBuiltinArray;

			private final Object object;

			public Descriminator(boolean isBuiltinArray, Object object) {
				this.isBuiltinArray = isBuiltinArray;
				this.object = object;
			}

			public Object getObject() {
				return object;
			};

			public boolean isBuiltinArray() {
				return isBuiltinArray;
			};
		}

		@Parameters
		public static Collection<Descriminator> data() {
			final ArrayList<Descriminator> retVal = new ArrayList<Descriminator>();
			retVal.add(desc(true, new boolean[0]));
			retVal.add(desc(true, new String[0]));
			retVal.add(desc(true, new long[0]));
			retVal.add(desc(false, new ArrayList<String>()));
			return retVal;
		}

		private static Descriminator desc(boolean isBuiltinArray, Object object) {
			return new Descriminator(isBuiltinArray, object);
		}

		@Parameter
		public Descriminator d;

		@Test
		public void test() {
			assertEquals(d.isBuiltinArray, isBuiltinArray(d.getObject()));
		}
	}

	public static class IllegalArgumentCheckTest {

		@Test(expected = IllegalArgumentException.class)
		public void test$nullNotAllowed() {
			isBuiltinArray(null);
		}
	}

}
