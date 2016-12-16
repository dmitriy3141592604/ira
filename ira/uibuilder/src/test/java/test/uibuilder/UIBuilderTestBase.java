package test.uibuilder;

import static org.i2g.ira.uibuilder.UIBuilderFactory.newUIBuilderFactory;

import java.lang.reflect.Method;

import org.i2g.ira.uibuilder.Tag;
import org.i2g.ira.uibuilder.Transformer;
import org.i2g.ira.uibuilder.UIBuilderFactory;
import org.junit.Assert;
import org.junit.Before;

public abstract class UIBuilderTestBase<ValueType extends Tag> extends Assert {

	protected UIBuilderFactory factory;

	protected Tag productRoot;

	@Before
	public void setUIBuilderFactoryBase() {
		productRoot = newRootValueType();
		factory = newUIBuilderFactory(productRoot, newMethodTransformer());
	}

	protected abstract Transformer<Method, Tag> newMethodTransformer();

	protected abstract Tag newRootValueType();

}