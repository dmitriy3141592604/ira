package test.uibuilder;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.Callable;

import org.i2g.ira.uibuilder.ProductVisitor;
import org.i2g.ira.uibuilder.Transformer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class UIBuilderTest extends UIBuilderTestBase<String> {

	private ProductVisitor visitor;

	interface I {
		J a();
	}

	interface J {
		I b();
	}

	@Test
	public void test$simpleBuild() {
		final I result = newRoot();
		assertNotNull(result.a());
	}

	@Test
	public void test$checkThatUsedArgumentInterfaceClass$beforeTestTypeWasHardcoded() {
		assertTrue(Callable.class.isAssignableFrom(factory.create(Callable.class).getClass()));
	}

	@Test
	public void test$someCombination() {
		assertNotNull(newRoot().a().b().a().b());
	}

	@Test
	// TODO Нужно перенести в блок нагрузочного тестирования
	public void test$manyCallsToCheckStackOverflow() {
		I root = newRoot();
		final int callDepth = 20;
		for (int i = 0; i < callDepth; ++i) {
			root = root.a().b();
		}
		assertNotNull(root);
	}

	@Test
	public void test$productCreation() {
		newRoot();
		assertEquals("root", productRoot.getValue());
	}

	/** TODO С этого места нужно форкать тест, и делать другой TestCase. **/
	@Before
	public void setUpUIBuilderTest() {
		visitor = new ProductVisitor();
	}

	@Test
	public void test$allMethodsCallAreLogged$onlyDeepthMode() {
		newRoot().a().b().a();

		productRoot.visit(visitor);

		assertEquals("<root><a><b><a></a></b></a></root>", visitor.resultLog());
	}

	@Test
	public void test$allMethodsCallAreLogged$brothersCreating() {
		final StringBuilder expectedResult = new StringBuilder();
		final StringBuilder $ = expectedResult;
		final I root = newRoot();

		$.append("<root>");

		root.a();
		$.append("<a></a>");

		root.a().b();
		$.append("<a><b></b></a>");

		root.a().b().a();
		$.append("<a><b><a></a></b></a>");

		root.a().b();
		$.append("<a><b></b></a>");

		root.a();
		$.append("<a></a>");

		$.append("</root>");

		productRoot.visit(visitor);

		assertEquals(expectedResult.toString(), visitor.resultLog());
	}

	interface Tagged {
		Tagged script(String src);
	}

	@Test
	@Ignore
	public void test$attributesCreation() {
		newTaggedRoot().script("//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js");

		productRoot.visit(visitor);

		assertEquals("<root><script src='//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script></root>", visitor.resultLog());
	}

	private I newRoot() {
		return factory.create(I.class);
	}

	private Tagged newTaggedRoot() {
		return factory.create(Tagged.class);
	}

	@Override
	protected String newRootValueType() {
		return "root";
	}

	@Override
	protected Transformer<Method, String> newMethodTransformer() {
		return new Transformer<Method, String>() {

			@Override
			public String transform(Method from, Object[] args) {
				final StringBuilder result = new StringBuilder();
				final String name = from.getName();
				result.append(name);
				final Parameter[] parameters = from.getParameters();
				for (final Parameter p : parameters) {
					result.append(" ");
					result.append(p.getName());
					result.append("=");
					result.append('"');
					result.append('"');
				}
				return result.toString();
			}
		};
	}

}
