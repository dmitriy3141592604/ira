package org.i2g.ira.naming.uiBuilder.tmp;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.Attributes;

import org.junit.Test;

class Attribute {
	private final String name;

	private final String value;

	public Attribute(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String asAttributeString() {
		return " " + name + "=\"" + value + "\"";
	}
}

class AttributeBuilder {
	public static Attribute asAttribute(String value) {
		final String methodName = new Exception().getStackTrace()[1].getMethodName();
		return new Attribute(methodName, value);
	}
}

class A {

	public Attribute href(String url) {
		return AttributeBuilder.asAttribute(url);
	}

	public Attribute klass(String url) {
		return AttributeBuilder.asAttribute(url);
	}
}

class Forest {

	private final List<Forest> children = new LinkedList<Forest>();

	private final List<Attribute> attributes = new LinkedList<Attribute>();

	private final String value;

	public Forest(String string) {
		value = string;
	}

	public void addAttribute(Attribute attr) {
		attributes.add(attr);
	}

	public Forest addChild(String st) {
		final Forest forest = new Forest(st);
		children.add(forest);
		return forest;
	}

	public void visit(Visitor visitor) {
		visitor.start(value);
		for (final Attribute a : attributes) {
			visitor.onAttribute(a);
		}
		visitor.afterAttributes();

		for (final Forest child : children) {
			child.visit(visitor);
		}
		visitor.end(value);
	}
}

interface P {
	H ret(Attribute... attributes);
}

interface H {
	H head(Attribute... attrs);

	H body(Attribute... attrs);

	H h1(Attribute... attrs);

	H p(Attribute... attrs);

	H div(Attribute... attrs);

	H text(String text);

	H a(Attribute... attrs);

	P other(Attributes... atts);
}

class UIBuilderFactory {

	private final Forest root;

	public UIBuilderFactory(Forest product) {
		this.root = product;
	}

	@SuppressWarnings("unchecked")
	public <T> T create(Class<?>... ifaces) {

		return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), ifaces, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//System.out.println(method.getName());
				return create(root.addChild(method.getName()), method.getReturnType());
			}

		});
	}

	@SuppressWarnings("unchecked")
	private <T> T create(Forest parent, Class<?>... ifaces) {
		return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), ifaces, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				final Forest child = parent.addChild(method.getName());
				if (method.getName().equals("a")) {
					//					System.out.println(method.getName());
				}
				for (final Object top : args) {
					//					System.out.println(top.getClass());
					if (top.getClass().toString().charAt(6) == '[') {
						for (final Object inner : (Object[]) top) {
							if (inner instanceof Attribute) {
								final Attribute attr = (Attribute) inner;
								//								 System.out.println(method.getName());
								child.addAttribute(attr);
							}
						}
					}
				}
				//System.out.println(method.getName());

				return create(child, method.getReturnType());
			}

		});
	}

}

class Visitor {

	private final StringBuilder out;

	public Visitor(StringBuilder out) {
		this.out = out;
	}

	public void afterAttributes() {
		out.append(">");

	}

	public void onAttribute(Attribute a) {
		out.append(a.asAttributeString());
	}

	public void start(String value) {
		out.append("<").append(value);
	}

	public void end(String value) {
		out.append("</").append(value).append(">");
	}

}

public class UIBuilderTest extends A {

	@Test
	public void test$001() {

		final Forest product = new Forest("html");
		final H html = new UIBuilderFactory(product).create(H.class, Runnable.class);

		if ("".isEmpty()) {
			@SuppressWarnings("unused")
			final H head = html.head();
			final H body = html.body();

			body.h1();
			body.a(href("http://some.url"));
			body.p().div();
		} else {
			@SuppressWarnings("unused")
			final H ret = html.body().p().other().ret();
		}

		final StringBuilder out = new StringBuilder();
		product.visit(new Visitor(out));

		assertEquals("<html><head></head><body><h1></h1><a href=\"http://some.url\"></a><p><div></div></p></body></html>", out.toString());

	}

	@SuppressWarnings("unused")
	private H newMethod(H html) {
		return html.body().p().other().ret();
	}

	@Test
	public void test$attributeName() {
		assertEquals(" href=\"http://some.org\"", href("http://some.org").asAttributeString());
	}

}
