package test.uibuilder;

import java.lang.reflect.Method;

import org.i2g.ira.uibuilder.Attribute;
import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.Tag;
import org.i2g.ira.uibuilder.TextElement;
import org.i2g.ira.uibuilder.Transformer;

public final class DefaultMethodTransformer implements Transformer<Method, Tag> {
	@Override
	public Tag transform(Method from, Object[] args) {
		final Element element = new Element(from.getName());
		if (args != null) {
			for (final Object arg : args) {
				if (arg instanceof Attribute) {
					element.addAttribute((Attribute) arg);
					continue;
				} else if (arg instanceof String) {
					return new TextElement(arg.toString());
				}

				throw new IllegalArgumentException("Argument with class: " + arg.getClass() + " not supported");
			}
		}
		return element;
	}
}