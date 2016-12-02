package uiserializer;

import static org.junit.Assert.assertEquals;

import org.i2g.ira.uibuilder.Element;
import org.junit.Test;

import testutils.RandomizedTest;

public class IndentTagVisitorSerializerTest implements RandomizedTest {

	@Test
	// TODO Нужно проверять полный функционал по выравниванию элементов
	// TODO нужно перенести в соответствующий проект
	public void test$TEMPORARY() {
		final StringBuilder sb = new StringBuilder();
		final IndentTagVisitorSerializer serializer = new IndentTagVisitorSerializer(sb);

		final String name = randomString();
		new Element(name).visit(serializer);

		assertEquals("\n<" + name + "></" + name + ">", sb.toString());

	}
}
