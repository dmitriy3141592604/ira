package test.uibuilder;

import static org.junit.Assert.assertEquals;

import org.i2g.ira.uibuilder.AttributeSerializer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import utils.Behavior;
import utils.Responsibility;

@Responsibility(type = AttributeSerializer.class, value = "Обеспечивает сериализацию модели html аттрибута")
public class StringAttributeSerializerTest extends StringAttributeSerializerTestBase {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	@Behavior("Аттрубут сериализуется как имя приравненное к значению в кавычках")
	public void test$SerializeSimpleString() {
		final String name = randomString();
		final String value = randomString();
		assertEquals(" " + name + "=\"" + value + "\"", transform(name, value));
	}

	@Test
	@Behavior("Специальные символы экранируются")
	public void test$protection() {
		assertEquals(" p=\"' &quot; &lt; &gt; &amp;\"", transform("p", "' \" < > &"));
	}

	@Test
	@Behavior("Если в значении атрибута только одинарные кавычки, то они не экранируюся, значение обрамляется двойными ковычками")
	public void test$preferred$singleQuote() {
		assertEquals(" p=\"'\"", transform("p", "\'"));
	}

	@Test
	@Behavior("Если в значении аттрибута только двойные кавычки, то значение обрамляется одинарными кавычками")
	public void test$preferred$doubleQuote$onlySingleQuotes() {
		assertEquals(" p=\'\"\'", transform("p", "\""));
	}

	@Test
	@Behavior("Если в значении используются оба вида кавычек, то значение обрамляется двойными кавычками, а внутри значения двойные экранируются.")
	public void test$preferred$doubleQuote$mixedMode() {
		assertEquals(" p=\"'&quot;\"", transform("p", "'\""));
	}

	@Test
	@Behavior("Нулевое значение означает аттрибут без значения")
	public void test$nullValueSerialization() {
		assertEquals(" selected", transform("selected", null));
	}

	@Test
	@Behavior("Нулевые имена аттрибутов недопустимы")
	public void test$nullableANameNotSupported() {
		exception.expect(IllegalArgumentException.class);

		transform(null, randomString());
	}

	@Test
	@Behavior("Пустые имена аттрибутов недопустимы")
	public void test$emptyANameNotSupported() {
		exception.expect(IllegalArgumentException.class);

		transform("", randomString());
	}

	@Test
	@Behavior("Пробелы недопустимы")
	public void test$blanksNotSupported() {
		exception.expect(IllegalArgumentException.class);

		transform(" hello", randomString());
	}

	@Test
	@Behavior("В качестве имени аттрибута можно использовать буквы, цифры, значи дефиса и подчеркивания")
	public void test$validName() {
		transform("h-ellO1_", randomString());
	}

}
