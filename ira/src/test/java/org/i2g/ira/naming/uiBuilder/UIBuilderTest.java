package org.i2g.ira.naming.uiBuilder;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.Attributes;

import org.junit.Test;

class Attribute {
	private String name;

	private String value;

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
		String methodName = new Exception().getStackTrace()[1].getMethodName();
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

	private List<Forest> children = new LinkedList<Forest>();

	private List<Attribute> attributes = new LinkedList<Attribute>();

	private String value;

	public Forest(String string) {
		value = string;
	}

	public void addAttribute(Attribute attr) {
		attributes.add(attr);
	}

	public Forest addChild(String st) {
		Forest forest = new Forest(st);
		children.add(forest);
		return forest;
	}

	public void visit(Visitor visitor) {
		visitor.start(value);
		for (Attribute a : attributes) {
			visitor.onAttribute(a);
		}
		visitor.afterAttributes();

		for (Forest child : children) {
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

	private Forest root;

	public UIBuilderFactory(Forest product) {
		this.root = product;
	}

	@SuppressWarnings("unchecked")
	public <T> T create(Class<?>... ifaces) {

		return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), ifaces, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println(method.getName());
				return create(root.addChild(method.getName()), method.getReturnType());
			}

		});
	}

	@SuppressWarnings("unchecked")
	private <T> T create(Forest parent, Class<?>... ifaces) {
		return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), ifaces, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Forest child = parent.addChild(method.getName());
				if (method.getName().equals("a")) {
					System.out.println(method.getName());
				}
				for (Object top : args) {
					System.out.println(top.getClass());
					if (top.getClass().toString().charAt(6) == '[') {
						for (Object inner : (Object[]) top) {
							if (inner instanceof Attribute) {
								Attribute attr = (Attribute) inner;
								// System.out.println(method.getName());
								child.addAttribute(attr);
							}
						}
					}
				}
				System.out.println(method.getName());

				return create(child, method.getReturnType());
			}

		});
	}

}

class Visitor {

	private StringBuilder out;

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

		Forest product = new Forest("html");
		H html = new UIBuilderFactory(product).create(H.class, Runnable.class);

		if ("".isEmpty()) {
			H head = html.head();
			H body = html.body();

			body.h1();
			body.a(href("http://some.url"));
			body.p().div();
		} else {
			H ret = html.body().p().other().ret();
		}

		StringBuilder out = new StringBuilder();
		product.visit(new Visitor(out));

		assertEquals(
				"<html><head></head><body><h1></h1><a href=\"http://some.url\"></a><p><div></div></p></body></html>",
				out.toString());

	}

	private H newMethod(H html) {
		return html.body().p().other().ret();
	}

	@Test
	public void test$attributeName() {
		assertEquals(" href=\"http://some.org\"", href("http://some.org").asAttributeString());
	}

}
