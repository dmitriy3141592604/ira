package test.uibuilder;

import java.lang.reflect.Method;

import org.i2g.ira.uibuilder.Attribute;
import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.Tag;
import org.i2g.ira.uibuilder.TextElement;
import org.i2g.ira.uibuilder.Transformer;

import logic.Condition;
import logic.ConditionSimple;
import logic.LogicOperatorUtils;
import utils.ExpandInnerArray;
import utils.Responsibility;
import utils.Value;

@Responsibility("Отвечает создание html элемента при использовании интерфейсного метода")
public class DefaultMethodTransformer implements Transformer<Method, Tag>, LogicOperatorUtils {

	private final ExpandInnerArray expander = new ExpandInnerArray();

	/**
	 * // TODO Если после атрибутов пойдет текст, то существующие аттрибуты зафейлятся
	 */
	@Override
	public Tag applay(Method from, Object[] args) {
		final Value<Tag> element = Value.newValue(new Element(from.getName()));

		final ConditionSimple stringFound = new ConditionSimple("stringFound");
		final ConditionSimple attributeFound = new ConditionSimple("attributeFound");

		final Condition hasArgs = new ConditionSimple("hasArgs", args != null);
		final ConditionSimple isString = new ConditionSimple("isString");
		final ConditionSimple isAttribute = new ConditionSimple("isAttribute");

		final Condition notAallowedArgument = not("notAllowedArgument", or("allowedArgument", isAttribute, isString));
		final Condition mixedState = and("mixedState", attributeFound, stringFound);

		hasArgs.run(() -> {
			expander.expand(args).forEach(arg -> {

				isAttribute.setValue(isAttribute(arg));
				isString.setValue(isString(arg));

				mixedState.run(() -> throwIllegalArgumentException("Attributes and text mixed"));
				notAallowedArgument.run(() -> throwIllegalArgumentException("Argument with class:", arg.getClass(), "not supported"));

				isAttribute.run(attributeFound::setOn);
				isAttribute.run(() -> ((Element) element.getValue()).addAttribute((Attribute) arg));

				isString.run(stringFound::setOn);
				isString.run(() -> element.setValue(new TextElement(arg.toString())));

			});
		});
		return element.getValue();

	}

	private boolean isString(Object arg) {
		return String.class.isAssignableFrom(arg.getClass());
	}

	private boolean isAttribute(Object arg) {
		return Attribute.class.isAssignableFrom(arg.getClass());
	}

	private void throwIllegalArgumentException(Object... s) {
		final StringBuilder message = new StringBuilder();
		String separator = "";
		for (final Object str : s) {
			// TOTO test me
			message.append(separator);
			message.append(str);
			separator = " ";
		}
		throw new IllegalArgumentException(message.toString());
	}

}