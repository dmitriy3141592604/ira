package test.uibuilder;

import static org.i2g.ira.uibuilder.AttributeBuilder.asAttribute;
import static org.i2g.ira.uibuilder.AttributeBuilder.asEmptyAttribute;
import static org.i2g.ira.uibuilder.AttributeBuilder.asNamedAttribute;
import static org.junit.Assert.assertEquals;

import org.i2g.ira.uibuilder.Attribute;
import org.junit.Test;

import utils.Behavior;

/**
 * Интерфейс для создания тегов налету в рамках тестирования
 */
interface AttributeBiulderTestTagHelper {

	default Attribute href(String url) {
		return asAttribute(url);
	};

	default Attribute src(String url) {
		return asAttribute(url);
	}

	default Attribute klass(String... names) {
		return asNamedAttribute("class", names);
	}

	default Attribute selected() {
		return asEmptyAttribute();
	};

}

public class AttributeBuilderTest extends AttributeBuilderTestBase {

	@Test
	@Behavior("AttributeBuilder использует имя метода для получения имени аттрибута")
	public void test$firstSample() {
		assertEquals(" href=\"http://\"", transform(attrs.href("http://")));
	}

	@Test
	@Behavior("Имя аттрибута можно подменить")
	public void test$usingAnnotation() {
		assertEquals(" class=\"selected\"", transform(attrs.klass("selected")));
	}

	@Test
	@Behavior("Можно использовать несколько значений. Они будут объеденены через пробел")
	public void test$stringArraySerialization() {
		assertEquals(" class=\"c1 c2 c3\"", transform(attrs.klass("c1", "c2", "c3")));
	}

	@Test
	@Behavior("При отсутствии значения создается аттрибут без значения")
	public void test$voidArgumetn() {
		assertEquals(" selected", transform(attrs.selected()));
	}
}
