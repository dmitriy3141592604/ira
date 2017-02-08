package utils;

import static org.junit.Assert.assertEquals;
import static utils.IraUtils.freeze;
import static utils.IraUtils.unfreeze;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import application.NameAware;
import application.WithMutableName;

public class ProcessContextFactoryTest extends ProcessContextFactoryTestBase {

	private NameWithAgeAware named;

	public interface NameWithAgeAware extends AgeAware, NameAware, WithMutableName {
	}

	@Before
	public void setUpProcessContextFactoryTest() {
		named = factory.create(NameWithAgeAware.class, new HashMap<String, Object>());
	}

	@Test
	@Behavior("Нулевые значения присваивать нельзя")
	public void test$null() {

		exception.expect(IllegalArgumentException.class);

		named.setName(null);
	}

	@Test
	@Behavior("Для значений типа Number по умолчанию возвращается -1")
	public void test$default$Long() {

		assertEquals(new Long(-1), named.getAge());
	}

	@Test
	@Behavior("Можно запретить установку значений")
	public void test$freeze() {
		named.setName(randomName);

		freeze(named);

		exception.expect(IllegalStateException.class);

		named.setName(randomName);
	}

	@Test
	@Behavior("Замороженные значения можно разморозить")
	public void test$unFreeze() {
		named.setName(randomName);

		freeze(named);
		unfreeze(named);

		named.setName(randomName);
	}

	@Test
	@Behavior("Нельзя повторно использоваь имя блокировка")
	public void test$freeze$oneLock() {

		exception.expect(IllegalArgumentException.class);

		freeze(named, randomName);
		freeze(named, randomName);

	}

}
