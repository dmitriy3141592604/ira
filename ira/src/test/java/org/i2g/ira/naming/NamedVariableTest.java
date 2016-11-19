package org.i2g.ira.naming;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import utils.IraTest;

public class NamedVariableTest extends IraTest {

	public interface NamedVariable {
		void setName(String variableName);
	}

	public static class Container<Value> implements NamedVariable {

		private Value value;

		private String variableName;

		private Object owner;

		public Container() {
			this(null);
		}

		public Container(Value value) {
			this.value = value;
		}

		public Container(Value value, Object owner) {
			this.value = value;
			this.owner = owner;
		}

		public Value getValue() {
			return value;
		}

		public void setValue(Value value) {
			if (owner instanceof User) {
				((User) owner).valueUpdated();
			}
			this.value = value;
		}

		@Override
		public void setName(String variableName) {
			this.variableName = variableName;
		}

		public String getVariableName() {
			return variableName;
		}

		public void setOwner(Object owner) {
			this.owner = owner;
		}
	}

	public static class GlobalValueHolder {
		public static String value = "";
	}

	public static class User {

		// private final Logger logger = Logger.getLogger(this.getClass());

		private final Container<String> myNewUserNameField = new Container<String>("initvalue", this);

		private final Container<String> additionalField = new Container<String>("some value", this);

		public Container<String> getUserName() {
			return myNewUserNameField;
		}

		public void valueUpdated() {
			GlobalValueHolder.value = "Value updated";
		}

		@Override
		public String toString() {
			return show(myNewUserNameField) + show(additionalField);
		}

		private String show(Container<String> var) {
			return var.variableName + " = " + var.value + " ";
		}
	}

	public static class SetVariableNameBeanPostProcessor implements BeanPostProcessor {

		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
			try {
				for (final Field field : bean.getClass().getDeclaredFields()) {
					final Class<?> fieldType = field.getType();
					if (NamedVariable.class.isAssignableFrom(fieldType)) {
						field.setAccessible(true);
						((NamedVariable) field.get(bean)).setName(field.getName());
					}
				}
				return bean;
			} catch (final Exception exception) {
				throw new RuntimeException(exception);
			}
		}

		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
			return bean;
		}

	}

	@Test
	public void testyName() {
		try (final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(User.class,
				SetVariableNameBeanPostProcessor.class)) {

			final User user = ctx.getBean(User.class);
			assertNotNull(user);

			assertEquals("", GlobalValueHolder.value);
			user.getUserName().setValue("new user name");
			assertEquals("Value updated", GlobalValueHolder.value);

			assertEquals("myNewUserNameField", user.getUserName().getVariableName());
			assertEquals("myNewUserNameField = new user name additionalField = some value ", user.toString());
		}
	}

}
