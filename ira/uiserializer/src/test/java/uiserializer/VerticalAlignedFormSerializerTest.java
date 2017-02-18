package uiserializer;

import org.junit.Test;

/**
 * {@linkplain VerticalAlignedFormSerializer}
 * 
 * @author fdv.741
 *
 */
public class VerticalAlignedFormSerializerTest extends VerticalAlignedFormSerializerTestBase {

	private String type = randomString();

	private String label = randomString();

	private final HtmlInputModel inputModel = new HtmlInputModelBase() {

		@Override
		public String getLabel() {
			return label;
		}

		@Override
		public String getType() {
			return type;
		}

	};

	@Test
	public void test$type() {
		process(type = rs);
		assertSubstringExists(" type=\"" + rs + "\"", sb.toString());
	}

	@Test
	public void test$label() {
		process(label = rs);
		assertSubstringExists(rs + "</label>", sb.toString());
	}

	public void process(Object notUsed) {
		add(inputModel);
		process();

	}

}
