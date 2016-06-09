package utils;

import static org.junit.Assert.assertEquals;
import static utils.IraUtils.freeze;
import static utils.IraUtils.unfreeze;

import java.lang.reflect.Proxy;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ProcessContextFactoryTest extends ProcessContextFactoryTestBase {

	private Named named;

	// TODO rename
	public interface Named {
		String getName();

		String setName(String string);

		Long getAge();
	}

	@Before
	public void setUpProcessContextFactoryTest() {
		named = factory.create(Named.class, new HashMap<String, Object>());
	}

	@Test
	@Behavior("�� ��������� ���� ����� �������� �������� � ��� ��������")
	public void testAssignFieldValue() {

		named.setName(randomName);

		assertEquals(randomName, named.getName());
	}

	@Test
	@Behavior("�������� null ����������� ��� ��������")
	public void test$null() {

		exception.expect(IllegalArgumentException.class);

		named.setName(null);
	}

	@Test
	@Behavior("�� ����������� ��������, ������� ���������� ��������� ��������. String")
	public void test$default$String() {

		assertEquals("", named.getName());
	}

	@Test
	@Behavior("�� ����������� ��������, ������� ���������� ��������� ��������.Long")
	public void test$default$Long() {

		assertEquals(new Long(-1), named.getAge());
	}

	@Test
	@Behavior("��������� ������� ����� ����������")
	public void test$freeze() {
		named.setName(randomName);

		freeze(named);

		exception.expect(IllegalStateException.class);

		named.setName(randomName);
	}

	@Test
	@Behavior("��������� ������� ����� �����������")
	public void test$unFreeze() {
		named.setName(randomName);

		freeze(named);
		unfreeze(named);

		named.setName(randomName);
	}

	@Test
	@Behavior("��������� ������� ����� ���������� ����� ����������� ����� ���")
	public void test$freeze$oneLock() {

		exception.expect(IllegalArgumentException.class);

		freeze(named, randomName);
		freeze(named, randomName);

	}

}
