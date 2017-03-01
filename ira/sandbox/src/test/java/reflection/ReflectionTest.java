package reflection;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class ReflectionTest {

	public interface I {
		String name();
	}

	public interface J {
		String value();
	}

	public class C implements I, J {
		@Override
		public String name() {
			return "namE";
		}

		@Override
		public String value() {
			return "vAlue";
		}

	}

	public class D implements J {

		@Override
		public String value() {
			return "valuDe";
		}

	}

	@Test
	public void test$declared() throws Exception {
		print("-- I --");
		describe("I", I.class.getMethods());
		print("-- C.Decl --");
		describe("C", C.class.getDeclaredMethods());

		assertEquals("namE", I.class.getMethods()[0].invoke(new C()));
		assertEquals(true, I.class.isAssignableFrom(C.class));
		final Method nameMethod = C.class.getMethods()[0];
		print("name declaringClass:", nameMethod.getDeclaringClass());

		final C c = new C();
		final D d = new D();

		assertEquals(true, I.class.isAssignableFrom(nameMethod.getDeclaringClass()));

		final I i = (I) Proxy.newProxyInstance(getClass().getClassLoader(), new Class<?>[] { I.class, J.class }, (proxy, method, args) -> {
			if (method.getDeclaringClass().isAssignableFrom(d.getClass())) {
				return method.invoke(d, args);
			}
			return "unknown";
		});

		assertEquals("valuDe", ((J) i).value());

	}

	private void describe(String string, Method[] methods) {
		for (final Method method : methods) {
			print(string + ".method:", method);
		}
	}

	void print(Object... args) {
		for (final Object arg : args) {
			System.out.print(" " + arg);
		}
		System.out.println();
	}
}
