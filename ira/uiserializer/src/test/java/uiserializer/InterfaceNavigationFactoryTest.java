package uiserializer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.support.WithId;

public class InterfaceNavigationFactoryTest extends InterfaceNavigationFactoryTestBase {

	public static interface WithIdDerivedInterface extends WithId {

	}

	public static interface WithIdDerivedInterfaceRedefinition extends WithId {

		@Override
		String id();

	}

	public static interface HelpInterface {

		String getStringReturnMethod();

		void getVoidReturnMethod();

		WithId getField();

		WithIdDerivedInterface getDerivedIdField();

		WithIdDerivedInterfaceRedefinition getDerivedIdFieldRedefinition();

	}

	@Test
	public void test$stringReturnValue$isEmpty() {
		assertEquals("", buildFrom.getStringReturnMethod());
	}

	@Test
	public void test$voidMethodsAllowed() {
		buildFrom.getVoidReturnMethod();
	}

	@Test
	public void test$WithId$ExactlyInterface() {
		assertEquals("HelpInterface.getField", buildFrom.getField().id());
	}

	@Test
	public void test$WithId$DerivedInterface() {
		assertEquals("HelpInterface.getDerivedIdField", buildFrom.getDerivedIdField().id());
	}

	@Test
	public void test$WithId$DerivedInterfaceRedefinition() {
		assertEquals("HelpInterface.getDerivedIdFieldRedefinition", buildFrom.getDerivedIdFieldRedefinition().id());
	}

}
