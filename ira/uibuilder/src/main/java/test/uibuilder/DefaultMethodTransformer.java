package test.uibuilder;

import java.lang.reflect.Method;

import org.i2g.ira.uibuilder.Attribute;
import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.Tag;
import org.i2g.ira.uibuilder.TextElement;
import org.i2g.ira.uibuilder.Transformer;

import utils.ExpandInnerArray;
import utils.Responsibility;

@Responsibility("Отвечает создание html элемента при использовании интерфейсного метода")
public final class DefaultMethodTransformer implements Transformer<Method, Tag> {

	private final ExpandInnerArray expander = new ExpandInnerArray();

	@Override
	public Tag transform(Method from, Object[] args) {
		final Element element = new Element(from.getName());
		if (args != null) {
			// TODO Если после атрибутов пойдет текст, то существующие аттрибуты зафейлятся
			boolean attributeFound = false;
			for (final Object arg : expander.expand(args)) {
				if (arg instanceof Attribute) {
					attributeFound = true;
					element.addAttribute((Attribute) arg);
					continue;
				} else if (arg instanceof String) {
					if (attributeFound) {
						throw new IllegalArgumentException("Attributes and text mixed");
					}
					return new TextElement(arg.toString());
				}
				throw new IllegalArgumentException("Argument with class: " + arg.getClass() + " not supported");
			}
		}
		return element;
	}
}