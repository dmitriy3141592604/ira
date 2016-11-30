package uiserializer;

import static org.junit.Assert.assertEquals;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;
import org.i2g.ira.uibuilder.TagVisitor;
import org.i2g.ira.uibuilder.UIBuilderFactory;
import org.junit.Before;

import test.uibuilder.DefaultMethodTransformer;
import test.uibuilder.TagVisitorSerializer;
import testutils.RandomizedTest;

public abstract class VerticalAlignedFormSerializerTestBase implements RandomizedTest {

	protected StringBuilder sb;
	private Element productRoot;
	private UIBuilderFactory factory;
	private HTMLElements elements;
	private VerticalAlignedFormSerializer serializer;
	private TagVisitor visitor;
	protected String rs;

	@Before
	public final void setUpVerticalAlignedFormSerializerTestBase() {
		sb = new StringBuilder();
		rs = randomString();
		productRoot = new Element("html");
		factory = new UIBuilderFactory(productRoot, new DefaultMethodTransformer());
		elements = factory.create(HTMLElements.class);
		serializer = new VerticalAlignedFormSerializer(elements);
		visitor = new TagVisitorSerializer(sb);
	}

	protected void process() {
		serializer.serialize();

		productRoot.visit(visitor);
	}

	protected void add(HtmlInputModel inputModel) {
		serializer.add(inputModel);
	}

	protected void assertSubstringExists(final String substring, final String actual) {
		if (!actual.contains(substring)) {
			final String message = "Expected substring: " + substring;
			final Object expected = "";
			assertEquals(message, expected, actual);
		}
	}

}
